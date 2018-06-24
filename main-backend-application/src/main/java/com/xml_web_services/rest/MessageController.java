package com.xml_web_services.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml_web_services.domain.Message;
import com.xml_web_services.domain.User;
import com.xml_web_services.help_classes.MessageDTO;
import com.xml_web_services.spring_services.MessageService;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8081", "http://localhost:8082",
"http://localhost:8082"}, maxAge = 3600, allowCredentials = "true")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Message> saveMessage(@RequestBody MessageDTO messageDTO, HttpServletRequest request) {
		return new ResponseEntity<>(this.messageService.save(messageDTO, (User)request.getSession().getAttribute("user")), HttpStatus.OK);
	}
}
