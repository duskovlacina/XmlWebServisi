package com.xml_web_services.spring_services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.Review;
import com.xml_web_services.repositories.AccomodationRepository;
import com.xml_web_services.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	public List<Review> findByAccomodation(Accomodation accomodation) {
		return this.reviewRepository.findByAccomodationAndConfirmed(accomodation, true);
	}

	public Review saveReview(Review review) {
		Accomodation accomodation = this.accomodationRepository.findById(review.getAccomodation().getAccomodationId()).get();
		this.accomodationRepository.save(countGrade(accomodation, review.getGrade()));
		return this.reviewRepository.save(review);
	}
	
	public List<Review> findByAllowed(boolean allowed) {
		return this.reviewRepository.findByConfirmed(allowed);
	}

	public List<Review> fomdByAccomodationAndAllowed(long id, boolean allowed) {
		Accomodation a = this.accomodationRepository.findById(id).get();
		return this.reviewRepository.findByAccomodationAndConfirmed(a, true);
	}
	
	public boolean setConfirmed(long id, boolean confirmed) {
		Review review = this.reviewRepository.findById(id).get();
		review.setConfirmed(confirmed);
		this.reviewRepository.save(review);
		return true;
	}
	
	private Accomodation countGrade(Accomodation accomodation, int reviewGrade) {
		List<Review> reviews = this.reviewRepository.findByAccomodationAndConfirmed(accomodation, true);
		int gradeSum = reviewGrade;
		int gradeCounter = 1;
		for (Review review : reviews) {
			gradeCounter++;
			gradeSum += review.getGrade();
		}
		float grade = (float)gradeSum/gradeCounter;
		accomodation.setGrade(grade);
		return accomodation;
	}
	
}
