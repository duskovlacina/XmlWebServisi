package com.xml_web_services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xml_web_services.domain.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

	List<Message> findByReaded(boolean readed);
}
