package com.xml_web_services.services;

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

	
	public AccomodationType save(AccomodationType at) {
		return this.accomodationTypeRepository.save(at);
	}

	
	public List<AccomodationType> getTypes() {
		return this.accomodationTypeRepository.findAll();
	}

	public AccomodationType findById(Long id) {
		Optional<AccomodationType> accomodationTypeOptional = this.accomodationTypeRepository.findById(id);
		if (accomodationTypeOptional.isPresent()) {
			return accomodationTypeOptional.get();
		}else {
			return null;
		}
	}
	
	public boolean delete(Long id) {
		this.accomodationTypeRepository.deleteById(id);
		return true;
	}

}
