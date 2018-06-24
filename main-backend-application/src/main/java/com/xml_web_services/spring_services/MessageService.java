package com.xml_web_services.spring_services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.Message;
import com.xml_web_services.domain.User;
import com.xml_web_services.help_classes.MessageDTO;
import com.xml_web_services.repositories.AccomodationRepository;
import com.xml_web_services.repositories.AgentRepository;
import com.xml_web_services.repositories.MessageRepository;


@Service
public class MessageService {

	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private AgentRepository agentRepository;
	
	public Message save(MessageDTO messageDTO, User user) {
		Optional<Accomodation> accomodationOptional = this.accomodationRepository.findById(messageDTO.getAccomodationId());
		Message msg = new Message();
		msg.setAgent(accomodationOptional.get().getAgent());
		msg.setUser(user);
		msg.setReaded(false);
		msg.setMessagText(messageDTO.getContent());
		return this.messageRepository.save(msg);
	}
	
	public Message save(Message message) {
		return this.messageRepository.save(message);
	}
	
	public List<Message> findByUser(User user) {
		return this.messageRepository.findByUser(user);
	}
}
