package com.xml_web_services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xml_web_services.domain.AccomodationCategory;


@Repository
public interface AccomodationCategoryRepository extends JpaRepository<AccomodationCategory, Long>{
	
	AccomodationCategory findByCategoryName(int categoryName);
}
