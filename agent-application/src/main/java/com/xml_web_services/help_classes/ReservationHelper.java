package com.xml_web_services.help_classes;

import java.util.Date;

public class ReservationHelper {

	private Long accomodationId;
	private Date startDate;
	private Date endDate;
	private int numOfPersons;
	public Long getAccomodationId() {
		return accomodationId;
	}
	public void setAccomodationId(Long accomodationId) {
		this.accomodationId = accomodationId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getNumOfPersons() {
		return numOfPersons;
	}
	public void setNumOfPersons(int numOfPersons) {
		this.numOfPersons = numOfPersons;
	}
	
	
}
