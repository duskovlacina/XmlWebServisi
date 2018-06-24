package com.xml_web_services.configuration.endpoints;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.xml_web_services.domain.GetAllAccomodationTypeResponse;
import com.xml_web_services.domain.GetAllAccomodationTypes;
import com.xml_web_services.repositories.AccomodationTypeRepository;

@Endpoint
public class AccomodationTypeEndpoint {
	
	private static final String NAMESPACE = "http://com/xml_web_services/domain";

	@Autowired
	private AccomodationTypeRepository accomodationTypeRepository;
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllAccomodationTypes")
	@ResponsePayload
	public GetAllAccomodationTypeResponse getAccomodationType(@RequestPayload GetAllAccomodationTypes request) {
		GetAllAccomodationTypeResponse response = new GetAllAccomodationTypeResponse();
		response.getAccomodationType().addAll(this.accomodationTypeRepository.findAll());
		return response;
	}
}
