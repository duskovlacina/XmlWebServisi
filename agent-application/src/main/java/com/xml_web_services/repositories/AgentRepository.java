package com.xml_web_services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xml_web_services.domain.Agent;



@Repository
public interface AgentRepository extends JpaRepository<Agent, Long>{

	Agent findByUsername(String username);
}
