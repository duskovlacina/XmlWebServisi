package com.xml_web_services.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter{
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/xws/*");
	}
	
	@Bean(name = "accomodationCategory")
	public DefaultWsdl11Definition accomodationServiceWsdl(XsdSchema accomodationCategorySchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AccomodationCategoryPort");
		wsdl11Definition.setLocationUri("/xws");
		wsdl11Definition.setTargetNamespace("http://com/xml_web_services/domain");
		wsdl11Definition.setSchema(accomodationCategorySchema);
		return wsdl11Definition;
	}

	@Bean(name = "accomodationType")
	public DefaultWsdl11Definition accomodationTypeWsdl(XsdSchema accomodationTypeSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AccomodationTypePort");
		wsdl11Definition.setLocationUri("/xws");
		wsdl11Definition.setTargetNamespace("http://com/xml_web_services/domain");
		wsdl11Definition.setSchema(accomodationTypeSchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "accomodation")
	public DefaultWsdl11Definition accomodationWsdl(XsdSchema accomodationSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AccomodationPort");
		wsdl11Definition.setLocationUri("/xws");
		wsdl11Definition.setTargetNamespace("http://com/xml_web_services/domain");
		wsdl11Definition.setSchema(accomodationSchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "reservation")
	public DefaultWsdl11Definition reservationWsdl(XsdSchema reservationSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ReservationPort");
		wsdl11Definition.setLocationUri("/xws");
		wsdl11Definition.setTargetNamespace("http://com/xml_web_services/domain");
		wsdl11Definition.setSchema(reservationSchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "message")
	public DefaultWsdl11Definition messageWsdl(XsdSchema messageSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("MessagePort");
		wsdl11Definition.setLocationUri("/xws");
		wsdl11Definition.setTargetNamespace("http://com/xml_web_services/domain");
		wsdl11Definition.setSchema(messageSchema);
		return wsdl11Definition;
	}
	
	@Bean(name = "review")
	public DefaultWsdl11Definition reviewWsdl(XsdSchema reviewSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ReviewPort");
		wsdl11Definition.setLocationUri("/xws");
		wsdl11Definition.setTargetNamespace("http://com/xml_web_services/domain");
		wsdl11Definition.setSchema(reviewSchema);
		return wsdl11Definition;
	}
	
}
