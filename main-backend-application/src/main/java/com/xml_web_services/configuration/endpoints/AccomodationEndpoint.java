package com.xml_web_services.configuration.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.AccomodationCategory;
import com.xml_web_services.domain.AccomodationType;
import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.Images;
import com.xml_web_services.domain.PricePlan;
import com.xml_web_services.domain.SendAccomodation;
import com.xml_web_services.domain.SendAccomodationResponse;
import com.xml_web_services.repositories.AccomodationCategoryRepository;
import com.xml_web_services.repositories.AccomodationRepository;
import com.xml_web_services.repositories.AccomodationTypeRepository;
import com.xml_web_services.repositories.AgentRepository;
import com.xml_web_services.repositories.ImagesRepository;
import com.xml_web_services.repositories.PricePlanRepository;

@Endpoint
public class AccomodationEndpoint {

	private static final String NAMESPACE = "http://com/xml_web_services/domain";

	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private PricePlanRepository pricePlanRepository;

	@Autowired
	private AccomodationCategoryRepository accomodationCategoryRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private ImagesRepository imagesRepository;
	
	@Autowired
	private AccomodationTypeRepository accomodationTypeRepository;
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "SendAccomodation")
	@ResponsePayload
	public SendAccomodationResponse sendAccomodation(@RequestPayload SendAccomodation request) {
		
		SendAccomodationResponse response = new SendAccomodationResponse();
		Accomodation acc = request.getAccomodation();
		Accomodation a = new Accomodation();
		a.setCity(acc.getCity());
		a.setCountry(acc.getCountry());
		a.setStreet(acc.getStreet());
		a.setStreetNumber(acc.getStreetNumber());
		Agent accomodationAgent = this.agentRepository.findByUsername(acc.getAgent().getUsername());
		accomodationAgent.setUsername(acc.getAgent().getUsername());
		accomodationAgent.setPassword(acc.getAgent().getPassword());
		accomodationAgent.setFirstName(acc.getAgent().getFirstName());
		accomodationAgent.setLastName(acc.getAgent().getLastName());
		accomodationAgent.setBusinessID(acc.getAgent().getBusinessID());
		
		a.setAgent(this.agentRepository.save(accomodationAgent));
		PricePlan pp = new PricePlan();
		pp.setPrice(acc.getPricePlan().getPrice());
		pp.setStartDate(acc.getPricePlan().getStartDate());
		pp.setEndDate(acc.getPricePlan().getEndDate());
		a.setPricePlan(this.pricePlanRepository.save(pp));
		a.setName(acc.getName());
		a.setDescription(acc.getDescription());
		a.setCapacity(acc.getCapacity());
		a.setBreakfast(acc.isBreakfast());
		a.setFb(acc.isFb());
		a.setHb(acc.isHb());
		
		a.setParking(acc.isParking());
		a.setKitchen(acc.isKitchen());
		
		a.setWifi(acc.isWifi());
		a.setTelevision(acc.isTelevision());
		a.setBathroom(acc.isBathroom());
		
		List<Images> images = new ArrayList<>();
		
		for (Images image : acc.getImages()) {
			Images i = new Images();
			i.setUrl(image.getUrl());
			images.add(this.imagesRepository.save(i));
		}
		a.getImages().addAll(images);
		
		AccomodationCategory cat = this.accomodationCategoryRepository.findByCategoryName(
				acc.getAccomodationCategory().getCategoryName());
		a.setAccomodationCategory(cat);
		
		AccomodationType type = this.accomodationTypeRepository.findByType(acc.getAccomodationType().getType());
		a.setAccomodationType(type);
		
		Accomodation accom = this.accomodationRepository.save(a);
		if (accom == null) {
			response.setStatus(false);
		} else {
			response.setStatus(true);
		}
		
		return response;
	}
}
