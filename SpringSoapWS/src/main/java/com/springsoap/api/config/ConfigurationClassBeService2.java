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
public class ConfigurationClassBeService2 {
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet2(ApplicationContext context) {
		MessageDispatcherServlet servlet2 = new MessageDispatcherServlet();
		servlet2.setApplicationContext(context);
		servlet2.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet2, "/ps/*"); //where you want to access
	}

	@Bean("customerRequestStatus123") // this is the name of the wsdl. eg. localhost:9098/ws/customerRequestStatus?wsdl
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema2) {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setPortTypeName("SoapWebServiceEndpoint2");//you can give anthing here 
		defaultWsdl11Definition.setLocationUri("/ws");
		defaultWsdl11Definition.setTargetNamespace("http://www.javatechie.com/spring/soap/api/loanEligibility");
		defaultWsdl11Definition.setSchema(schema2);
		return defaultWsdl11Definition;

		
		
		//trial on creating a new 
		
		
		
	
}
	
	@Bean
	public XsdSchema schema2() {
		return new SimpleXsdSchema(new ClassPathResource("loaneligibility.xsd"));//make sure the .api is the root
	}
	
	
}
