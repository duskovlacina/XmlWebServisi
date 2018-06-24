package com.xml_web_services.rest;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.Message;
import com.xml_web_services.services.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Message>> getNotReadMessages() {
		List<Message> messages = this.messageService.findByReadStatus(false);
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public Message replyMessage(@RequestBody Message message, HttpServletRequest request) {
		return this.messageService.answer(message, (Agent)request.getSession().getAttribute("agent"));
	}
}
