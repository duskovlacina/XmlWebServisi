package com.xml_web_services.configuration.soap_clients;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.SendAccomodation;
import com.xml_web_services.domain.SendAccomodationResponse;


public class AccomodationSoapClient extends WebServiceGatewaySupport{

	public SendAccomodationResponse sendAccomodation(Accomodation accomodation) {
		SendAccomodation request = new SendAccomodation();
		request.setAccomodation(accomodation);
		SendAccomodationResponse response = (SendAccomodationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("http://localhost:8080/xws/SendAccomodation"));
		
		return response;
	}
	
}

