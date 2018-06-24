package com.xml_web_services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.domain.Images;
import com.xml_web_services.repositories.ImagesRepository;

@Service
public class ImagesService{

	@Autowired
	private ImagesRepository imagesRepository;
	public Images save(Images im) {
		return this.imagesRepository.save(im);
	}

}
