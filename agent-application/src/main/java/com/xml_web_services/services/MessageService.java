package com.xml_web_services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.domain.Message;
import com.xml_web_services.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public List<Message> findByReadStatus(boolean readStatus) {
		return this.messageRepository.findByReaded(readStatus);
	}
}
