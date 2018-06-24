package com.xml_web_services.spring_services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.domain.AccomodationType;
import com.xml_web_services.repositories.AccomodationTypeRepository;

@Service
public class AccomodationTypeService {
	@Autowired
	private AccomodationTypeRepository accomodationTypeRepository;

	public AccomodationType findById(Long id) {
		Optional<AccomodationType> optional = this.accomodationTypeRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	public List<AccomodationType> getAll() {
		return this.accomodationTypeRepository.findAll();
	}
	
	public AccomodationType save(AccomodationType at) {
		return this.accomodationTypeRepository.save(at);
	}

	public boolean delete(Long id) {
		this.accomodationTypeRepository.deleteById(id);
		return true;
	}

}
