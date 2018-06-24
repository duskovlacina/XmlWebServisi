package com.xml_web_services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xml_web_services.domain.AccomodationType;


@Repository
public interface AccomodationTypeRepository extends JpaRepository<AccomodationType, Long>{

	AccomodationType findByType(String type);
}
