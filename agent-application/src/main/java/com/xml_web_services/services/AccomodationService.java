package com.xml_web_services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.configuration.soap_clients.AccomodationSoapClient;
import com.xml_web_services.configuration.soap_clients.ReservationSoapClient;
import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.PricePlan;
import com.xml_web_services.domain.Reservation;
import com.xml_web_services.domain.ReserveResponse;
import com.xml_web_services.domain.SendAccomodationResponse;
import com.xml_web_services.help_classes.ReservationHelper;
import com.xml_web_services.repositories.AccomodationRepository;
import com.xml_web_services.repositories.PricePlanRepository;
import com.xml_web_services.repositories.ReservationRepository;

@Service
public class AccomodationService{

	@Autowired
	private AccomodationRepository accomodationRepository;
	
	
	@Autowired
	private PricePlanRepository pricePlanRepository;
	
	@Autowired
	private AccomodationSoapClient accomodationClient;
	
	@Autowired
	private ReservationSoapClient reservationClient;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public List<Accomodation> getAccomodations() {
		return this.accomodationRepository.findAll();
	}
	
	public Accomodation save(Accomodation accomodation) {
		PricePlan pricePlan = this.pricePlanRepository.save(accomodation.getPricePlan());
		if (pricePlan == null) {
			return null;
		}else {
			return this.accomodationRepository.save(accomodation);
		}
	}

	public Accomodation findById(Long id) {
		Optional<Accomodation> accomodationOptional = this.accomodationRepository.findById(id);
		if (accomodationOptional.isPresent()) {
			return accomodationOptional.get();
		} else {
			return null;
		}
	}
	
	public Accomodation findByName(String name) {
		return this.accomodationRepository.findByName(name);
		
	}
	
	public void sendAccomodationToServer(Accomodation accomodation) {
		System.out.println("SENDING TO SERVER!!!!");
		System.out.println(accomodationClient);
		System.out.println(accomodation);
		SendAccomodationResponse accomodationResponse = accomodationClient.sendAccomodation(accomodation);
	}
	
	public Reservation tryToReserve(ReservationHelper helper, Agent agent) {
		Reservation reservation = new Reservation();
		reservation.setAccomodation(this.accomodationRepository.findById(helper.getAccomodationId()).get());
		System.out.println("ACCOMODATION ID OF RESERVATIONNNNNNN " + reservation.getAccomodation());
		reservation.setConfirmed(false);
		reservation.setCapacity(this.accomodationRepository.findById(helper.getAccomodationId()).get().getCapacity());
		reservation.setEndDate(helper.getEndDate());
		reservation.setStartDate(helper.getStartDate());
		reservation.setUser(null);
		
		int daysOfReservation = (int)( (helper.getEndDate().getTime() - helper.getStartDate().getTime()) / (3600000 * 24));
		float price = this.accomodationRepository.findById(helper.getAccomodationId()).get().getPricePlan().getPrice();
		reservation.setPrice(price * (daysOfReservation+1));
		
		
		ReserveResponse response = this.reservationClient.reserve(reservation);
		if (response.isState()) {
			return this.reservationRepository.save(reservation);
		}
		return null;
	}

}
