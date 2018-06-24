package com.xml_web_services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.Review;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

	List<Review> findByAccomodationAndConfirmed(Accomodation accomodation, boolean confirmed);
	List<Review> findByConfirmed(boolean allowed);
}
