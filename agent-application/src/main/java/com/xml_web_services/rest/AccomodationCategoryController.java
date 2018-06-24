package com.xml_web_services.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml_web_services.domain.AccomodationCategory;
import com.xml_web_services.services.AccomodationCategoryService;


@RestController
@RequestMapping("/accomodation-category")
public class AccomodationCategoryController {

	@Autowired
	private AccomodationCategoryService accomodationCategoryService;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AccomodationCategory>> getAllAccomodationCategories() {
		return new ResponseEntity<>(this.accomodationCategoryService.getAllAccomodationCategories(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccomodationCategory> updateAccomodationCategory(@RequestBody AccomodationCategory as) {
		AccomodationCategory accomodationCategory = this.accomodationCategoryService.save(as);
		if (accomodationCategory != null) return new ResponseEntity<>(accomodationCategory, HttpStatus.OK);
		return new ResponseEntity<>(accomodationCategory, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccomodationCategory> create(@RequestBody AccomodationCategory as) {
		AccomodationCategory accomodationCategory = this.accomodationCategoryService.save(as);
		if (accomodationCategory != null) return new ResponseEntity<>(accomodationCategory, HttpStatus.CREATED);
		return new ResponseEntity<>(accomodationCategory, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AccomodationCategory> deleteAccomodationCategory(@PathVariable Long id) {
		boolean flag = this.accomodationCategoryService.delete(id);
		if (flag) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
