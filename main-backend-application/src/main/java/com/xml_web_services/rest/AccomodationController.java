package com.xml_web_services.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.Reservation;
import com.xml_web_services.domain.Review;
import com.xml_web_services.domain.User;
import com.xml_web_services.help_classes.AccomodationSearch;
import com.xml_web_services.help_classes.ReservationHelper;
import com.xml_web_services.spring_services.AccomodationService;
import com.xml_web_services.spring_services.ReviewService;



@RestController
@RequestMapping("/accomodation")
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8081"
		, "http://localhost:8088"}, maxAge = 3600, allowCredentials = "true")
public class AccomodationController {

	@Autowired
	private AccomodationService accomodationService;
	
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Accomodation>> getAccomodations() {
		return new ResponseEntity<>(this.accomodationService.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Accomodation> getAccomodation(@PathVariable long id) {
		return new ResponseEntity<>(this.accomodationService.findById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Accomodation>> searchAccomodations(@RequestBody AccomodationSearch accomodationSearch) {
		return new ResponseEntity<>(this.accomodationService.searchAccomodations(accomodationSearch), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reserve", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Reservation> reserveAccomodation(@RequestBody ReservationHelper reservationHelper,
								HttpServletRequest request) {
		Reservation reservation = this.accomodationService.
				saveReservation(reservationHelper, (User) request.getSession().getAttribute("user"));
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/review", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Review> createReview(HttpServletRequest request, @RequestBody Review review, @PathVariable long id) {
		review.setUserId(((User)request.getSession().getAttribute("user")).getUserId());
		review.setConfirmed(false);
		review.setAccomodation(this.accomodationService.findById(id));
		return new ResponseEntity<>(this.reviewService.saveReview(review), HttpStatus.OK);
	}
	
	
}
