package com.xml_web_services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xml_web_services.domain.PricePlan;


@Repository
public interface PricePlanRepository extends JpaRepository<PricePlan, Long>{

}
