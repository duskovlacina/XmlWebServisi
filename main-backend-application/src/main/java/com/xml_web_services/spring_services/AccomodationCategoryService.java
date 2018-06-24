package com.xml_web_services.spring_services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.domain.AccomodationCategory;
import com.xml_web_services.repositories.AccomodationCategoryRepository;


@Service
public class AccomodationCategoryService {

	@Autowired
	private AccomodationCategoryRepository accomodationCategoryRepository;

	public AccomodationCategory save(AccomodationCategory as) {
		return this.accomodationCategoryRepository.save(as);
	}

	public boolean deleteAccomodationService(Long id) {
		this.accomodationCategoryRepository.deleteById(id);
		return true;
	}
	
	public List<AccomodationCategory> getAllAccomodationCategories() {
		return this.accomodationCategoryRepository.findAll();
	}

	public AccomodationCategory findById(Long id) {
		Optional<AccomodationCategory> optional = this.accomodationCategoryRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}


}
