 package com.springsoapBe.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.springsoapBe.api.service.CustomerValidation;
import com.webserviceSoapbe.pojos.CustomerRequest;


//simple endpoint 
 /**
  * 
  * @author Yuvi
  *
  */
 
@Endpoint 
public class BeCustomerEndPoint {
	//xs get the namespacd 
	
	private static final String NAMESPACE="urn:bes-external-call.informatica.mdm";

	@Autowired
	private CustomerValidation customerRequest;//service class
	
	@PayloadRoot(namespace = NAMESPACE, localPart = "CustomerRequest")
	@ResponsePayload
	public void getCustomerResponse(@RequestPayload CustomerRequest request) {
		
		 	customerRequest.DoCustomerValidation(request);
		 //you can have a return object here !!
		 	
	}

}
