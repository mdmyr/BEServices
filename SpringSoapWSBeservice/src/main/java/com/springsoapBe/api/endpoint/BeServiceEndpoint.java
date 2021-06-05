package com.springsoapBe.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.springsoapBe.api.service.BeServiceImpl;
import com.webserviceSoapbe.pojos.ExternalCallRequest;
import com.webserviceSoapbe.pojos.ExternalCallResponse;
import com.webserviceSoapbe.pojos.Trigger;

//endpoin configuration


@Endpoint
public class BeServiceEndpoint {

		//define the namespace 
		private static final String NAMESPACE="urn:bes-external-call.informatica.mdm";
		
		//Inject the service here 
		@Autowired
		private BeServiceImpl beservice;
		
		@PayloadRoot(namespace = NAMESPACE,localPart = "Trigger")
		@ResponsePayload
		public ExternalCallResponse getBEvalidation(@RequestPayload ExternalCallRequest request) {
			return beservice.getBeValidation(request);
			
		}
		
		
}
