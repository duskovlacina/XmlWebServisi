package com.xml_web_services.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml_web_services.domain.Reservation;
import com.xml_web_services.services.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Reservation>> getResrvationsForUser(HttpServletRequest request) {
		List<Reservation> reservations = reservationService.getAll(); 
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/activate", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Reservation> activate(@PathVariable long id) {
		return new ResponseEntity<>(reservationService.activate(id), HttpStatus.OK);
	}
}
