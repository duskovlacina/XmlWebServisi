package com.xml_web_services.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.xml_web_services.configuration.soap_clients.AccomCategorySoapClient;
import com.xml_web_services.configuration.soap_clients.AccomTypeSoapClient;
import com.xml_web_services.configuration.soap_clients.AccomodationSoapClient;
import com.xml_web_services.configuration.soap_clients.MessageSoapClient;
import com.xml_web_services.configuration.soap_clients.ReservationSoapClient;
import com.xml_web_services.configuration.soap_clients.ReviewSoapClient;

@Configuration
public class SoapConfiguration {
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.xml_web_services.domain");
		return marshaller;
	}

	@Bean
	public AccomTypeSoapClient accomodationTypeSoapClient(Jaxb2Marshaller marshaller) {
		AccomTypeSoapClient client = new AccomTypeSoapClient();
		client.setDefaultUri("http://localhost:8080/xws/accomodationType.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public AccomCategorySoapClient accomodationCategorySoapClient(Jaxb2Marshaller marshaller) {
		AccomCategorySoapClient client = new AccomCategorySoapClient();
		client.setDefaultUri("http://localhost:8080/xws/accomodationCategory.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public MessageSoapClient messageClient(Jaxb2Marshaller marshaller) {
		MessageSoapClient client = new MessageSoapClient();
		client.setDefaultUri("http://localhost:8080/xws/message.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	@Bean
	public ReservationSoapClient reservationClient(Jaxb2Marshaller marshaller) {
		ReservationSoapClient client = new ReservationSoapClient();
		client.setDefaultUri("http://localhost:8080/xws/reservation.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	
	@Bean
	public AccomodationSoapClient accomodationClient(Jaxb2Marshaller marshaller) {
		AccomodationSoapClient client = new AccomodationSoapClient();
		client.setDefaultUri("http://localhost:8080/xws/accomodation.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
	
	@Bean
	public ReviewSoapClient reviewClient(Jaxb2Marshaller marshaller) {
		ReviewSoapClient client = new ReviewSoapClient();
		client.setDefaultUri("http://localhost:8080/xws/review.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
