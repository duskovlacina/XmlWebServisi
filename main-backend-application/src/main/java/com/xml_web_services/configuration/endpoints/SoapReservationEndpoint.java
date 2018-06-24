package com.xml_web_services.configuration.endpoints;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.GetAgentReservations;
import com.xml_web_services.domain.GetAgentReservationsResponse;
import com.xml_web_services.domain.Reservation;
import com.xml_web_services.domain.ReserveRequest;
import com.xml_web_services.domain.ReserveResponse;
import com.xml_web_services.domain.SendReservation;
import com.xml_web_services.domain.SendReservationResponse;
import com.xml_web_services.repositories.AccomodationRepository;
import com.xml_web_services.repositories.AgentRepository;
import com.xml_web_services.repositories.ReservationRepository;
import com.xml_web_services.spring_services.AgentService;
import com.xml_web_services.spring_services.ReservationService;

@Endpoint
public class SoapReservationEndpoint {

	private static final String NAMESPACE = "http://com/xml_web_services/domain";

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private AgentRepository agentRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private AccomodationRepository accomodationRepository;

	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAgentReservations")
	@ResponsePayload
	public GetAgentReservationsResponse getAgentReservations(@RequestPayload GetAgentReservations request) {
		Agent agent = this.agentRepository.findByUsername(request.getAgentUsername());
		List<Reservation> agentReservations = new ArrayList<>();
		List<Accomodation> accomodations = this.accomodationRepository.findByAgent(agent);
		for (Accomodation accomodation : accomodations) {
			agentReservations.addAll(this.reservationRepository.findByAccomodation(accomodation));
		}
		GetAgentReservationsResponse response = new GetAgentReservationsResponse();
		response.getReservations().addAll(agentReservations);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE, localPart = "SendReservation")
	@ResponsePayload
	public SendReservationResponse sendReservation(@RequestPayload SendReservation request) {
		System.out.println("2222");
		Reservation res = request.getReservation();
		Reservation reservation = this.reservationRepository.findByAccomodationAndStartDateAndEndDate(
				this.accomodationRepository.findByName(request.getReservation().getAccomodation().getName()), request.getReservation().getStartDate(), request.getReservation().getEndDate());
				
		System.out.println("e33333" + res.isConfirmed() + reservation);
		reservation.setConfirmed(res.isConfirmed());
		System.out.println("4444");
		this.reservationService.save(reservation);
		System.out.println("5555");
		SendReservationResponse response = new SendReservationResponse();
		System.out.println("66666");
		response.setState(true);
		System.out.println("77777");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE, localPart = "ReserveRequest")
	@ResponsePayload
	public ReserveResponse reserve(@RequestPayload ReserveRequest request) {

		Date reservationStartingDate = request.getReservation().getStartDate();
		Date reservationEndingDate = request.getReservation().getEndDate();
		boolean b = true;

		Accomodation a = this.accomodationRepository.findByName(request.getReservation().getAccomodation().getName());
		List<Reservation> reservations = this.reservationService.findByAccomodation(a);

		for (Reservation r : reservations) {
			if (reservationStartingDate.compareTo(r.getStartDate()) > 0) {
				if (reservationStartingDate.compareTo(r.getEndDate()) < 0) {
					b = false;
					break;
				}
			} else if (reservationStartingDate.compareTo(r.getStartDate()) < 0) {
				if (reservationEndingDate.compareTo(r.getEndDate()) >= 0) {
					b = false;
					break;
				}
			} else if (reservationStartingDate.compareTo(r.getStartDate()) == 0) {
				b = false;
				break;
			}
		}

		ReserveResponse response = new ReserveResponse();

		if (!b) {
			response.setState(false);
		} else {
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(reservationEndingDate);
			int reservationEndingYear = calendar.get(Calendar.YEAR);

			calendar.setTime(reservationStartingDate);
			int reservationStartingYear = calendar.get(Calendar.YEAR);

			String accomodationName = request.getReservation().getAccomodation().getName();
			Accomodation accomodation = this.accomodationRepository.findByName(accomodationName);

			Reservation reservation = new Reservation();
			reservation.setConfirmed(request.getReservation().isConfirmed());
			reservation.setAccomodation(accomodation);

			reservation.setStartDate(request.getReservation().getStartDate());
			reservation.setEndDate(request.getReservation().getEndDate());

			reservation.setPrice(request.getReservation().getPrice());
			reservation.setCapacity(request.getReservation().getCapacity());

			this.reservationService.save(reservation);
			response.setState(true);
			return response;
		}
		return response;
	}

}
