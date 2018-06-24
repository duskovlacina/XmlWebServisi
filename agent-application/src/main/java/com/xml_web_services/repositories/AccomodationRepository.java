package com.xml_web_services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xml_web_services.domain.Accomodation;


@Repository
public interface AccomodationRepository extends JpaRepository<Accomodation, Long>{

	Accomodation findByName(String name);
}
