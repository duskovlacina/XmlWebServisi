package com.xml_web_services.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.Review;
import com.xml_web_services.repositories.ReviewRepository;


@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	public Review save(Review review) {
		return this.reviewRepository.save(review);
	}

	public boolean approve(Review review) {
		Review r = this.reviewRepository.findById(review.getReviewId()).get();
		r.setConfirmed(true);
		this.reviewRepository.save(r);
		return true;
	}

	public boolean decline(Review review) {
		Review r = this.reviewRepository.findById(review.getReviewId()).get();
		r.setConfirmed(false);
		this.reviewRepository.save(r);
		return true;
	}

	public List<Review> findByAccomodation(Accomodation accomodation) {
		return this.reviewRepository.findByAccomodationAndConfirmed(accomodation, true);
	}
	
	public List<Review> findAll() {
		return this.reviewRepository.findAll();
	}

}
