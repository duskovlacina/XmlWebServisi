package com.xml_web_services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.Agent;


@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long>{

	Accomodation findByName(String name);
	List<Accomodation> findByAgent(Agent agent);
}
