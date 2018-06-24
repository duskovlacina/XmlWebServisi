package com.xml_web_services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.configuration.soap_clients.ReservationSoapClient;
import com.xml_web_services.domain.Reservation;
import com.xml_web_services.repositories.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationSoapClient reservationClient;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public List<Reservation> getAll() {
		return reservationRepository.findAll();
	}
	
	public Reservation save(Reservation reservation) {
		return this.reservationRepository.save(reservation);
	}

	public void removeAll() {
		this.reservationRepository.deleteAll();
	}

	public Reservation activate(long id) {
		Optional<Reservation> opt = this.reservationRepository.findById(id);
		if (!opt.isPresent()) return null;
		Reservation r = opt.get();
		r.setConfirmed(true);
		reservationClient.sendReservation(r);
		return this.reservationRepository.save(r);
	}
}
