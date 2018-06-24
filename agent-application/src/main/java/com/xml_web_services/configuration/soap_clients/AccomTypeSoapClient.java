package com.xml_web_services.configuration.soap_clients;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.xml_web_services.domain.GetAllAccomodationTypeResponse;
import com.xml_web_services.domain.GetAllAccomodationTypes;

public class AccomTypeSoapClient extends WebServiceGatewaySupport{
	public GetAllAccomodationTypeResponse getAccomodationType(Long id) {
		GetAllAccomodationTypes request = new GetAllAccomodationTypes();
		request.setId(id);
		
		GetAllAccomodationTypeResponse response = (GetAllAccomodationTypeResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("http://localhost:8080/xws/GetAllAccomodationTypes"));
		
		return response;
	}
}
