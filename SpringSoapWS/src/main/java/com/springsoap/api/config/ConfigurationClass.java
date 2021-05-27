package com.springsoap.api.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class ConfigurationClass {
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*"); 
	}

	@Bean("customerRequestStatus") // this is the name of the wsdl. eg. localhost:9098/ws/customerRequestStatus?wsdl
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setPortTypeName("SoapWebServiceEndpoint");
		defaultWsdl11Definition.setLocationUri("/ws");
		defaultWsdl11Definition.setTargetNamespace("http://www.javatechie.com/spring/soap/api/loanEligibility");
		defaultWsdl11Definition.setSchema(schema);
		return defaultWsdl11Definition;

		
		
		//trial on creating a new 
		
		
		
	
}
	
	@Bean
	public XsdSchema schema() {
		return new SimpleXsdSchema(new ClassPathResource("loaneligibility.xsd"));
	}
	
	
	
/** 
	
	//creating a dummy url	
	@Bean("customerRequestStatus2") // this is the name of the wsdl. eg. localhost:9098/ws/customerRequestStatus?wsdl
	public DefaultWsdl11Definition defaultWsdl11Definition2(XsdSchema schema2) {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setPortTypeName("SoapWebServiceEndpoint");
		defaultWsdl11Definition.setLocationUri("/ws");
		defaultWsdl11Definition.setTargetNamespace("urn:bes-external-call.informatica.mdm");
		defaultWsdl11Definition.setSchema(schema2);
		return defaultWsdl11Definition;
	//trial on creating a new 
	
}
	@Bean
	public XsdSchema schema2() {
		return new SimpleXsdSchema(new ClassPathResource("BeService.wsdl"));
	}
	
	
	*/
	
	
	
}
