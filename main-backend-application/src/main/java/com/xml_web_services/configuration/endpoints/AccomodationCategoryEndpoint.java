package com.xml_web_services.configuration.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.xml_web_services.domain.GetAllAccomodationCategories;
import com.xml_web_services.domain.GetAllAccomodationCategoriesResponse;
import com.xml_web_services.repositories.AccomodationCategoryRepository;


@Endpoint
public class AccomodationCategoryEndpoint {

	private static final String NAMESPACE = "http://com/xml_web_services/domain";

	@Autowired
	private AccomodationCategoryRepository accomodationCategoryRepository;

	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAllAccomodationCategories")
	@ResponsePayload
	public GetAllAccomodationCategoriesResponse getAccomodations(@RequestPayload GetAllAccomodationCategories request) {
		GetAllAccomodationCategoriesResponse response = new GetAllAccomodationCategoriesResponse();
		response.getAccomodationCategory().addAll(accomodationCategoryRepository.findAll());
		return response;
	}
}
