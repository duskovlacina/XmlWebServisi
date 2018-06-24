package com.xml_web_services.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml_web_services.domain.Agent;
import com.xml_web_services.spring_services.AgentService;



@RestController
@RequestMapping("/agent")
@CrossOrigin(origins = {"http://localhost:8081",
"http://localhost:8082", "http://localhost:8088"}, maxAge = 3600, allowCredentials = "true")
public class AgentController {

	private AgentService agentService;
	
	public AgentController(AgentService agentService) {
		this.agentService = agentService;;
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Agent> save(@RequestBody Agent agent) {
		Agent savedAgent = this.agentService.save(agent);
		if (savedAgent != null) {
			return new ResponseEntity<>(savedAgent, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
