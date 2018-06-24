package com.xml_web_services.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml_web_services.domain.Review;
import com.xml_web_services.spring_services.ReviewService;



@RestController
@RequestMapping("/review")
@CrossOrigin(origins = {"http://localhost:8081",
"http://localhost:8082", "http://localhost:8088"}, maxAge = 3600, allowCredentials = "true")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/{id}")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Review>> getReviewsByAccomodation(@PathVariable long id){
		return new ResponseEntity<>(this.reviewService.findByAllowed(true), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pending", method = RequestMethod.GET)
	public ResponseEntity<List<Review>> reviews(){
		return new ResponseEntity<>(this.reviewService.findByAllowed(false), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Review>> getReviews(){
		return new ResponseEntity<>(this.reviewService.findByAllowed(true), HttpStatus.OK);
	}
	
	
	// TODO: should be id/approve and PUT
	@GetMapping("/approve/{id}")
	@RequestMapping(value = "/approve/{id}", method = RequestMethod.GET)
	public ResponseEntity<Review> approve(@PathVariable long id) {
		this.reviewService.setConfirmed(id, true);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@RequestMapping(value = "/decline/{id}", method = RequestMethod.GET)
	public ResponseEntity<Review> decline(@PathVariable long id) {
		this.reviewService.setConfirmed(id, false);
		return new ResponseEntity<>( HttpStatus.OK);
	}
}
