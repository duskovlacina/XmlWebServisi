package com.xml_web_services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.Message;
import com.xml_web_services.domain.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
	List<Message> findByUser(User user);
	List<Message> findByAgentAndReaded(Agent agent, boolean readed);
}
