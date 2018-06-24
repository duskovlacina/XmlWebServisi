package com.xml_web_services.spring_services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.PricePlan;
import com.xml_web_services.domain.Reservation;
import com.xml_web_services.domain.User;
import com.xml_web_services.help_classes.AccomodationSearch;
import com.xml_web_services.help_classes.ReservationHelper;
import com.xml_web_services.repositories.AccomodationRepository;
import com.xml_web_services.repositories.PricePlanRepository;
import com.xml_web_services.repositories.ReservationRepository;


@Service
public class AccomodationService{

	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private PricePlanRepository pricePlanRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public Accomodation findById(Long id) {
		Optional<Accomodation> optional = this.accomodationRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	
	public Accomodation saveAccomodation(Accomodation accomodation) {
		PricePlan p = this.pricePlanRepository.save(accomodation.getPricePlan());
		if (p == null) return null;
		return this.accomodationRepository.save(accomodation);
	}
	
	public List<Accomodation> getAll() {
		return this.accomodationRepository.findAll();
	}
	
	
	
	public Reservation saveReservation(ReservationHelper reservationHelper, User user) {
		Reservation reservation = new Reservation();
		Optional<Accomodation> accomodation = this.accomodationRepository.findById(reservationHelper.getAccomodationId());
		
		reservation.setUser(user);
		reservation.setAccomodation(accomodation.get());

		reservation.setEndDate(reservationHelper.getEndDate());
		reservation.setStartDate(reservationHelper.getStartDate());
		reservation.setCapacity(reservationHelper.getNumOfPersons());
		
		reservation.setConfirmed(false);
		int days = (int)( (reservationHelper.getEndDate().getTime() - reservationHelper.getStartDate().getTime()) / (3600 * 1000 * 24));
		reservation.setPrice((days + 1) * accomodation.get().getPricePlan().getPrice());
		
		return this.reservationRepository.save(reservation);
	}
	
	
	public List<Accomodation> searchAccomodations(AccomodationSearch accomodationSearch) {
		
		List<Accomodation> accomodations = this.accomodationRepository.findAll();
		List<Accomodation> possbileAccomodations = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		boolean isReservable = true;
		boolean inPricePlan = false;
		for (Accomodation accomodation : accomodations) {
			isReservable = true;
			calendar.setTime(accomodationSearch.getStartDate());
			int startingYear = calendar.get(Calendar.YEAR);
			calendar.setTime(accomodationSearch.getEndDate());
			int endingYear = calendar.get(Calendar.YEAR);
			
			if (accomodation.getPricePlan().getStartDate() <= startingYear && startingYear <= accomodation.getPricePlan().getEndDate()) {
				if (accomodation.getPricePlan().getStartDate() <= endingYear && endingYear <= accomodation.getPricePlan().getEndDate()) {
					inPricePlan = true;
				}
			}
			if (inPricePlan && accomodationSearch.getCapacity() <= accomodation.getCapacity()) {
				if (accomodationSearch.getAccomodationTypeValue() != null) {
					if (!accomodationSearch.getAccomodationTypeValue().equals(accomodation.getAccomodationType().getType())) {
						isReservable = false;
					}
				}
				
				if (accomodationSearch.getAccomodationCategoryValue() > 0) {
					if (accomodationSearch.getAccomodationCategoryValue() != accomodation.getAccomodationCategory().getCategoryName()) {
						isReservable = false;
					}
				}
				
				if (accomodationSearch.isTelevision() && !accomodation.isTelevision()) {
					isReservable = false;
				}
				
				if (accomodationSearch.isBathroom() && !accomodation.isBathroom()) {
					isReservable = false;
				}
				if (accomodationSearch.isBreakfast() && !accomodation.isBreakfast()) {
					isReservable = false;
				}
				
				if (accomodationSearch.isFb() && !accomodation.isFb()) {
					isReservable = false;
				}
				
				if (accomodationSearch.isWifi() && !accomodation.isWifi()) {
					isReservable = false;
				}
				if (accomodationSearch.isHb() && !accomodation.isHb()) {
					isReservable = false;
				}
				
				if (accomodationSearch.isKitchen() && !accomodation.isKitchen()) {
					isReservable = false;
				}
				
				if (accomodationSearch.isParking() && !accomodation.isParking()) {
					isReservable = false;
				}
				
				if (!accomodationSearch.getCountry().equals(accomodation.getCountry())) {
					isReservable = false;
				}
				
				if (isReservable) {
					possbileAccomodations.add(accomodation);
				}
			}
		}
		
		List<Accomodation> reservableAccomodations = new ArrayList<>();
        boolean isReservable2 = true;
		Date endDate = new Date(accomodationSearch.getEndDate().getTime());
		Date startDate = new Date(accomodationSearch.getStartDate().getTime());
        
        for (Accomodation a : possbileAccomodations) {
			List<Reservation> reservations = this.reservationRepository.findByAccomodation(a);
			for (Reservation r : reservations) {
				if (startDate.compareTo(r.getStartDate()) == 0){
					isReservable2 = false;
				}else if (startDate.compareTo(r.getStartDate()) < 0) {
					if (endDate.compareTo(r.getEndDate()) >= 0) {
						isReservable2 = false;
					}
				}else if(startDate.compareTo(r.getStartDate()) > 0) {
					if (startDate.compareTo(r.getStartDate()) < 0) {
						isReservable2 = false;
					}
				} 
			}
			if (isReservable2) {
				reservableAccomodations.add(a);
			}
			isReservable2 = true;
		}
		return reservableAccomodations;
	}

}
