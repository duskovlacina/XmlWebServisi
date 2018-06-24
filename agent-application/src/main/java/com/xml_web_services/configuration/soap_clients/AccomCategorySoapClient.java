package com.xml_web_services.configuration.soap_clients;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.xml_web_services.domain.GetAllAccomodationCategories;
import com.xml_web_services.domain.GetAllAccomodationCategoriesResponse;

public class AccomCategorySoapClient extends WebServiceGatewaySupport{

	public GetAllAccomodationCategoriesResponse getAccomodationCategories(Long id) {
		GetAllAccomodationCategories request = new GetAllAccomodationCategories();
		request.setId(id);
		
		GetAllAccomodationCategoriesResponse response = (GetAllAccomodationCategoriesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("http://localhost:8080/xws/GetAllAccomodationCategories"));
		return response;
	}
}
