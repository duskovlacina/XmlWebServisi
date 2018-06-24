package com.xml_web_services.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml_web_services.domain.AccomodationType;
import com.xml_web_services.spring_services.AccomodationTypeService;


@RestController
@RequestMapping("/accomodation-type")
@CrossOrigin(origins = {"http://localhost:8081",
"http://localhost:8082", "http://localhost:8088"}, maxAge = 3600, allowCredentials = "true")
public class AccomodationTypeController {

	@Autowired
	private AccomodationTypeService accomodationTypeService;
	
	@GetMapping
	public ResponseEntity<List<AccomodationType>> getAllAccomodationTypes() {
		return new ResponseEntity<>(this.accomodationTypeService.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccomodationType> createAccomodationType(@RequestBody AccomodationType at) {
		AccomodationType type = this.accomodationTypeService.save(at);
		if (type != null) {
			return new ResponseEntity<>(type, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(type, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccomodationType> updateAccomodationType(@RequestBody AccomodationType at) {
		AccomodationType accomodationType = this.accomodationTypeService.save(at);
		if (accomodationType != null) {
			return new ResponseEntity<>(accomodationType, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(accomodationType, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AccomodationType> deleteAccomodationType(@PathVariable Long id) {
		System.out.println("ADELETEEEEEEEEEEEEE  " + id);
		boolean flag = this.accomodationTypeService.delete(id);
		if (flag) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
