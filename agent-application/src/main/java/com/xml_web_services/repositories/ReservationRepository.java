package com.xml_web_services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.Reservation;
import com.xml_web_services.domain.User;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	List<Reservation> findByAccomodation(Accomodation accomodation);
	List<Reservation> findByUser(User user);
}
