package com.xml_web_services.configuration.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.GetAgentReviews;
import com.xml_web_services.domain.GetAgentReviewsResponse;
import com.xml_web_services.domain.Review;
import com.xml_web_services.repositories.AccomodationRepository;
import com.xml_web_services.repositories.AgentRepository;
import com.xml_web_services.repositories.ReviewRepository;


@Endpoint
public class SoapReviewEndpoint {

	private static final String NAMESPACE = "http://com/xml_web_services/domain";

	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "GetAgentReviews")
	@ResponsePayload
	public GetAgentReviewsResponse getAgentReviews(@RequestPayload GetAgentReviews request) {
		
		List<Review> reviews = new ArrayList<>();
		GetAgentReviewsResponse response = new GetAgentReviewsResponse();
		
		Agent agent = this.agentRepository.findByUsername(request.getAgentUsername());
		if (agent != null) {
			List<Accomodation> accomodations = this.accomodationRepository.findByAgent(agent);
			for (Accomodation accomodation: accomodations) {
				reviews.addAll(this.reviewRepository.findByAccomodation(accomodation));
			}
			response.getReviews().addAll(reviews);
		}
		return response;
	}
	
}
