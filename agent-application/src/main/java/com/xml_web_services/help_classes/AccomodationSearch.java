package com.xml_web_services.help_classes;

import java.util.Date;

public class AccomodationSearch {
	private String country;
	private String accomodationTypeValue;
	private int accomodationCategoryValue;
	private int capacity;
	private Date endDate;
	private Date startDate;
	private boolean fb;
	private boolean hb;
	private boolean kitchen;
	private boolean breakfast;
	private boolean parking;
	private boolean bathroom;
	private boolean television;
	private boolean wifi;
	
	public AccomodationSearch() {}

	public Date getEndDate() {
		return endDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isFb() {
		return fb;
	}

	public void setFb(boolean fb) {
		this.fb = fb;
	}

	public boolean isHb() {
		return hb;
	}

	public void setHb(boolean hb) {
		this.hb = hb;
	}

	public boolean isBathroom() {
		return bathroom;
	}

	public void setBathroom(boolean bathroom) {
		this.bathroom = bathroom;
	}

	public boolean isTelevision() {
		return television;
	}

	public void setTelevision(boolean television) {
		this.television = television;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getAccomodationTypeValue() {
		return accomodationTypeValue;
	}

	public void setAccomodationTypeValue(String accomodationTypeValue) {
		this.accomodationTypeValue = accomodationTypeValue;
	}

	public int getAccomodationCategoryValue() {
		return accomodationCategoryValue;
	}

	public void setAccomodationCategoryValue(int accomodationCategoryValue) {
		this.accomodationCategoryValue = accomodationCategoryValue;
	}

	public boolean isKitchen() {
		return kitchen;
	}

	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}

	public boolean isBreakfast() {
		return breakfast;
	}

	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}
	
	
}
