package com.xml_web_services.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml_web_services.domain.AccomodationType;
import com.xml_web_services.services.AccomodationTypeService;


@RestController
@RequestMapping("/accomodation-type")
public class AccomodationTypeController {

	@Autowired
	private AccomodationTypeService accomodationTypeService;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AccomodationType>> getAllAccomodationTypes() {
		return new ResponseEntity<>(this.accomodationTypeService.getTypes(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccomodationType> createAccomodationType(@RequestBody AccomodationType accomodationType) {
		AccomodationType type = this.accomodationTypeService.save(accomodationType);
		if (type != null) {
			return new ResponseEntity<>(type, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(type, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccomodationType> updateAccomodationType(@RequestBody AccomodationType accomodationType) {
		AccomodationType type = this.accomodationTypeService.save(accomodationType);
		if (type != null) {
			return new ResponseEntity<>(type, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(type, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AccomodationType> deleteAccomodationType(@PathVariable Long id) {
		boolean flag = this.accomodationTypeService.delete(id);
		if (flag) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
	
}
