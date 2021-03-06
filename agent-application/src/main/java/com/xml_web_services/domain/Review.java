//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.23 at 09:26:50 PM CEST 
//


package com.xml_web_services.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Review complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Review">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reviewId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="grade" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="reviewText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="confirmed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="accomodation" type="{http://com/xml_web_services/domain}Accomodation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Review", propOrder = {
    "reviewId",
    "userId",
    "grade",
    "reviewText",
    "confirmed",
    "accomodation"
})
@Entity
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    protected long reviewId;
	@Column
    protected long userId;
	@Column
    protected int grade;
    @XmlElement(required = true)
    @Column(nullable = false)
    protected String reviewText;
    @Column
    protected boolean confirmed;
    @XmlElement(required = true)
    @ManyToOne
    protected Accomodation accomodation;

    /**
     * Gets the value of the reviewId property.
     * 
     */
    public long getReviewId() {
        return reviewId;
    }

    /**
     * Sets the value of the reviewId property.
     * 
     */
    public void setReviewId(long value) {
        this.reviewId = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     */
    public void setUserId(long value) {
        this.userId = value;
    }

    /**
     * Gets the value of the grade property.
     * 
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Sets the value of the grade property.
     * 
     */
    public void setGrade(int value) {
        this.grade = value;
    }

    /**
     * Gets the value of the reviewText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     * Sets the value of the reviewText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewText(String value) {
        this.reviewText = value;
    }

    /**
     * Gets the value of the confirmed property.
     * 
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * Sets the value of the confirmed property.
     * 
     */
    public void setConfirmed(boolean value) {
        this.confirmed = value;
    }

    /**
     * Gets the value of the accomodation property.
     * 
     * @return
     *     possible object is
     *     {@link Accomodation }
     *     
     */
    public Accomodation getAccomodation() {
        return accomodation;
    }

    /**
     * Sets the value of the accomodation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Accomodation }
     *     
     */
    public void setAccomodation(Accomodation value) {
        this.accomodation = value;
    }

}
