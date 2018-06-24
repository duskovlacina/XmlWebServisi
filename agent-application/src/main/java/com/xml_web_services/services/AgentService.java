package com.xml_web_services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.configuration.soap_clients.AccomCategorySoapClient;
import com.xml_web_services.configuration.soap_clients.AccomTypeSoapClient;
import com.xml_web_services.configuration.soap_clients.AccomodationSoapClient;
import com.xml_web_services.configuration.soap_clients.MessageSoapClient;
import com.xml_web_services.configuration.soap_clients.ReservationSoapClient;
import com.xml_web_services.configuration.soap_clients.ReviewSoapClient;
import com.xml_web_services.domain.AccomodationCategory;
import com.xml_web_services.domain.AccomodationType;
import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.GetAgentMessages;
import com.xml_web_services.domain.GetAgentMessagesResponse;
import com.xml_web_services.domain.GetAgentReservationsResponse;
import com.xml_web_services.domain.GetAgentReviewsResponse;
import com.xml_web_services.domain.GetAllAccomodationCategoriesResponse;
import com.xml_web_services.domain.GetAllAccomodationTypeResponse;
import com.xml_web_services.domain.Message;
import com.xml_web_services.domain.Reservation;
import com.xml_web_services.domain.Review;
import com.xml_web_services.repositories.AccomodationCategoryRepository;
import com.xml_web_services.repositories.AccomodationRepository;
import com.xml_web_services.repositories.AccomodationTypeRepository;
import com.xml_web_services.repositories.AgentRepository;
import com.xml_web_services.repositories.MessageRepository;
import com.xml_web_services.repositories.ReservationRepository;
import com.xml_web_services.repositories.ReviewRepository;

@Service
public class AgentService {

	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private AccomodationTypeRepository accomodationTypeRepository;
	
	@Autowired
	private AccomodationCategoryRepository accomodationCategoryRepository;
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private AccomodationSoapClient accomodationClient;
	
	@Autowired
	private MessageSoapClient messageClient;
	
	@Autowired
	private ReservationSoapClient reservationClient;
	
	@Autowired
	private AccomTypeSoapClient accomTypeClient;

	@Autowired
	private AccomCategorySoapClient accomCategoryClient;
	
	@Autowired
	private ReviewSoapClient reviewClient;
	
	public Agent findByUsername(Agent agent) {
		Agent a = this.agentRepository.findByUsername(agent.getUsername());
		if (a != null) return a;
		return null;
	}

	public void syncWithServer(Agent agent) {
		synchronizeTypes();
		synchronizeCategories();
		synchronizeReservations(agent);
		synchronizeMessages(agent);
		synchronizeReviews(agent);
	}
	
	private void synchronizeMessages(Agent agent) {
		GetAgentMessagesResponse agentMessagesResponse = this.messageClient.getMessages(agent.getUsername());
		for (Message msg : agentMessagesResponse.getMessages()) {
			Message message = new Message();
			message.setAgent(this.agentRepository.findByUsername(msg.getAgent().getUsername()));
			message.setMessagText(msg.getMessagText());
			message.setReaded(false);
			message.setUser(msg.getUser());
			this.messageRepository.save(message);
		}
	}
	
	private void synchronizeReservations(Agent agent) {
		this.reservationService.removeAll();
		GetAgentReservationsResponse reservationResponse = this.reservationClient.getReservations(agent);
		
		for (Reservation r : reservationResponse.getReservations()) {
			Reservation reservation = new Reservation();
			reservation.setConfirmed(r.isConfirmed());
			reservation.setCapacity(r.getCapacity());
			
			reservation.setAccomodation(this.accomodationRepository.findByName(r.getAccomodation().getName()));
			reservation.setPrice(r.getPrice());
			reservation.setEndDate(r.getEndDate());
			reservation.setStartDate(r.getStartDate());
			
			
			reservation.setUser(null);
			this.reservationRepository.save(reservation);
		}
	}
	
	private void synchronizeReviews(Agent agent) {
		this.reviewRepository.deleteAll();
		GetAgentReviewsResponse reviewResponse = this.reviewClient.getReviews(agent);
		for (Review r: reviewResponse.getReviews()) {
			Review review = new Review();
			review.setAccomodation(this.accomodationRepository.findByName(r.getAccomodation().getName()));
			
			review.setReviewText(r.getReviewText());
			review.setConfirmed(r.isConfirmed());
			review.setUserId(r.getUserId());
			review.setGrade(r.getGrade());
			
			this.reviewRepository.save(review);
		}
	}
	
	private void synchronizeCategories() {
		GetAllAccomodationCategoriesResponse accomodationCategoryResponse = accomCategoryClient.getAccomodationCategories(0L);
		System.out.println("SYNC CATEGORIES " + accomodationCategoryResponse.getAccomodationCategory().size());
		for (AccomodationCategory ac: accomodationCategoryResponse.getAccomodationCategory()) {
			AccomodationCategory accomodationCategory = this.accomodationCategoryRepository.findByCategoryName(ac.getCategoryName());
			if (accomodationCategory == null) { 
				accomodationCategory = new AccomodationCategory();
				accomodationCategory.setCategoryName(ac.getCategoryName());
				this.accomodationCategoryRepository.save(accomodationCategory);
			}
		}
	}
	
	private void synchronizeTypes() {
		System.out.println("SYNCC TYPESSSS ");
		GetAllAccomodationTypeResponse getAllAccomodationTypeResponse = accomTypeClient.getAccomodationType(0L);
		System.out.println(getAllAccomodationTypeResponse.getAccomodationType().size());
		for (AccomodationType ac: getAllAccomodationTypeResponse.getAccomodationType()) {
			AccomodationType type = this.accomodationTypeRepository.findByType(ac.getType());
			if (type == null) { 
				type = new AccomodationType();
				type.setType(ac.getType());
				this.accomodationTypeRepository.save(type);
			}
		}
	}
}
