package com.xml_web_services.spring_services;

import org.springframework.stereotype.Service;

import com.xml_web_services.domain.Agent;
import com.xml_web_services.repositories.AgentRepository;

@Service
public class AgentService {

	private AgentRepository agentRepository;
	
	public AgentService(AgentRepository agentRepository) {
		this.agentRepository = agentRepository;;
	}

	public Agent save(Agent agent) {
		return this.agentRepository.save(agent);
	}

	public Agent findByUsername(String username) {
		return this.agentRepository.findByUsername(username);
	}
}
