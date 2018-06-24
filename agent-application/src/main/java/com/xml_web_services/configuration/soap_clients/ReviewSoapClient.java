package com.xml_web_services.configuration.soap_clients;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.GetAgentReviews;
import com.xml_web_services.domain.GetAgentReviewsResponse;

public class ReviewSoapClient extends WebServiceGatewaySupport {

	public GetAgentReviewsResponse getReviews(Agent agent) {
		GetAgentReviews request = new GetAgentReviews();
		request.setAgentUsername(agent.getUsername());
		
		GetAgentReviewsResponse response = (GetAgentReviewsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request,
						new SoapActionCallback("http://localhost:8080/xws/GetAgentReviews"));
		
		return response;
	}
}
