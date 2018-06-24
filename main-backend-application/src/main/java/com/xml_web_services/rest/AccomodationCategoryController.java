package com.xml_web_services.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml_web_services.domain.AccomodationCategory;
import com.xml_web_services.spring_services.AccomodationCategoryService;
import com.xml_web_services.spring_services.AccomodationService;

@RestController
@RequestMapping("/accomodation-category")
@CrossOrigin(origins = {"http://localhost:8081",
		"http://localhost:8082", "http://localhost:8088"}, maxAge = 3600, allowCredentials = "true")
public class AccomodationCategoryController {

	@Autowired
	private AccomodationCategoryService accomodationCategoryService;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AccomodationCategory>> getAllAccomodationServices() {
		return new ResponseEntity<>(this.accomodationCategoryService.getAllAccomodationCategories(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccomodationCategory> create(@RequestBody AccomodationCategory as) {
		AccomodationCategory category = this.accomodationCategoryService.save(as);
		if (category != null) return new ResponseEntity<>(category, HttpStatus.CREATED);
		return new ResponseEntity<>(category, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AccomodationService> deleteAccomodationService(@PathVariable Long id) {
		boolean deleted = this.accomodationCategoryService.deleteAccomodationService(id);
		if (deleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccomodationCategory> updateAccomodationService(@RequestBody AccomodationCategory as) {
		AccomodationCategory category = this.accomodationCategoryService.save(as);
		if (category != null) {
			return new ResponseEntity<>(category, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(category, HttpStatus.BAD_REQUEST);
		}
	}
}
