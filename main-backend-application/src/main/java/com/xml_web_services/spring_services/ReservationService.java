package com.xml_web_services.spring_services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.Reservation;
import com.xml_web_services.domain.User;
import com.xml_web_services.repositories.AccomodationRepository;
import com.xml_web_services.repositories.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	public Reservation save(Reservation reservation) {
		return this.reservationRepository.save(reservation);
	}
	
	public Reservation findById(long id) {
		Optional<Reservation> reservationOptional = this.reservationRepository.findById(id);
		if (reservationOptional.isPresent()) {
			return reservationOptional.get();
		}else {
			return null;
		}
	}
	
	public List<Reservation> getAll() {
		return this.reservationRepository.findAll();
	}

	public boolean deleteReservation(long id) {
		this.reservationRepository.deleteById(id);
		return true;
	}
	
	public List<Reservation> findByAccomodation(Accomodation accomodation) {
		return this.reservationRepository.findByAccomodation(accomodation);
	}
	
	public List<Reservation> getByUser(User user) {
		return this.reservationRepository.findByUser(user);
	}

	public List<Reservation> getByAgent(Agent agent) {
		List<Accomodation> accomodations = this.accomodationRepository.findByAgent(agent);
		List<Reservation> reservations = new ArrayList<>();
		for (Accomodation a: accomodations) {
			reservations.addAll(this.reservationRepository.findByAccomodation(a));
		}
		return reservations;
	}
	
}
