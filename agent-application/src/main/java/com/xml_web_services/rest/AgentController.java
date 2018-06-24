package com.xml_web_services.rest;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xml_web_services.domain.Agent;
import com.xml_web_services.services.AgentService;

@RestController
@RequestMapping("/agent")
public class AgentController {

	private static final Logger logger = LoggerFactory.getLogger(AgentController.class);
	
	@Autowired
	private AgentService agentService;
	
	
	@PostMapping("/login")
	public ResponseEntity<Agent> login(@RequestBody Agent agent, HttpServletRequest request) {
		logger.debug("REST request to login into system!");
		Agent a = this.agentService.findByUsername(agent);
		if (a != null && a.getPassword().equals(agent.getPassword())) {
			request.getSession().setAttribute("agent", a);
			this.agentService.syncWithServer(a);
			return new ResponseEntity<>(a, HttpStatus.OK);	
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/logout")
	public ResponseEntity logout(HttpServletRequest request) {
		logger.debug("REST request to logout from the system!");
		request.getSession().removeAttribute("agent");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/account")
	public ResponseEntity account(HttpServletRequest request) {
		logger.debug("REST request to logout from the system!");
		Agent agent = (Agent)request.getSession().getAttribute("agent");
		if (agent != null) return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
