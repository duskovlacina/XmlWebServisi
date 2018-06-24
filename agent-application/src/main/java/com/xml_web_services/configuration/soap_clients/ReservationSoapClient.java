package com.xml_web_services.configuration.soap_clients;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.GetAgentReservations;
import com.xml_web_services.domain.GetAgentReservationsResponse;
import com.xml_web_services.domain.Reservation;
import com.xml_web_services.domain.ReserveRequest;
import com.xml_web_services.domain.ReserveResponse;
import com.xml_web_services.domain.SendReservation;
import com.xml_web_services.domain.SendReservationResponse;


public class ReservationSoapClient extends WebServiceGatewaySupport {

	public GetAgentReservationsResponse getReservations(Agent agent) {
		GetAgentReservations request = new GetAgentReservations();
		request.setAgentUsername(agent.getUsername());;
		GetAgentReservationsResponse response = (GetAgentReservationsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("http://localhost:8080/xws/GetAgentReservations"));
		return response;

	}
	
	public ReserveResponse reserve(Reservation reservation) {
		ReserveRequest request = new ReserveRequest();
		request.setReservation(reservation);
		ReserveResponse response = (ReserveResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("http://localhost:8080/xws/ReserveRequest"));
		return response;
	}
	
	public SendReservationResponse sendReservation(Reservation reservation) {
		SendReservation request = new SendReservation();
		request.setReservation(reservation);
		SendReservationResponse response = (SendReservationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("http://localhost:8080/xws/SendReservation"));
		return response;
	}
	
}