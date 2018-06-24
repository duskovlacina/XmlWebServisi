package com.xml_web_services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.configuration.soap_clients.MessageSoapClient;
import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.Message;
import com.xml_web_services.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private MessageSoapClient messageSoapClient;
	
	public List<Message> findByReadStatus(boolean readStatus) {
		return this.messageRepository.findByReaded(readStatus);
	}
	
	public Message answer(Message message, Agent agent) {
		Message reply = new Message();
		Message original = this.messageRepository.findById(message.getMessageId()).get();
		reply.setAgent(agent);
		reply.setMessagText(message.getMessagText());
		reply.setReaded(true);
		reply.setUser(null);
		reply.setReplyId(original.getReplyId());
		
		// messageSoapClient.
		
		//original.setReaded(true);
		this.messageRepository.save(original);
		return reply;
	}
}
