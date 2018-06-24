package com.xml_web_services.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
public class XsdSchemasConfiguration {
	@Bean
	public XsdSchema accomodationCategorySchema() {
		return new SimpleXsdSchema(new ClassPathResource("./xsd_schemas/accomodationCategory.xsd"));
	}
	
	@Bean
	public XsdSchema accomodationTypeSchema() {
		return new SimpleXsdSchema(new ClassPathResource("./xsd_schemas/accomodationType.xsd"));
	}
	
	@Bean
	public XsdSchema accomodationSchema() {
		return new SimpleXsdSchema(new ClassPathResource("./xsd_schemas/accomodation.xsd"));
	}
	
	@Bean
	public XsdSchema reservationSchema() {
		return new SimpleXsdSchema(new ClassPathResource("./xsd_schemas/reservation.xsd"));
	}
	
	@Bean
	public XsdSchema messageSchema() {
		return new SimpleXsdSchema(new ClassPathResource("./xsd_schemas/message.xsd"));
	}
	
	
	@Bean
	public XsdSchema reviewSchema() {
		return new SimpleXsdSchema(new ClassPathResource("./xsd_schemas/review.xsd"));
	}
	
	
}
