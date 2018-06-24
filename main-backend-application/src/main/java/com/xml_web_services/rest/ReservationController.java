package com.xml_web_services.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml_web_services.domain.Reservation;
import com.xml_web_services.domain.User;
import com.xml_web_services.spring_services.ReservationService;



@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins = {"http://localhost:8081",
"http://localhost:8082", "http://localhost:8088"}, maxAge = 3600, allowCredentials = "true")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Reservation> cancel(@PathVariable Long id) {
		this.reservationService.deleteReservation(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<Reservation>> getResrvationsForUser(HttpServletRequest request) {
		return new ResponseEntity<>(this.reservationService.getByUser((User)request.getSession().getAttribute("user")),
				HttpStatus.OK);
	}
}
