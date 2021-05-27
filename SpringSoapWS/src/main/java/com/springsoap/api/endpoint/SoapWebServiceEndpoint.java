package com.springsoap.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.springsoap.api.service.LoanEligibilityService;
import com.webserviceSoap.pojos.Acknowledgement;
import com.webserviceSoap.pojos.CustomerRequest;

@Endpoint
public class SoapWebServiceEndpoint {
	//get it from xs
		private static final String NAMESPACE="http://www.javatechie.com/spring/soap/api/loanEligibility";
		
		@Autowired
		private LoanEligibilityService service;
		
		@PayloadRoot(namespace = NAMESPACE,localPart = "CustomerRequest") // localPar is 
		@ResponsePayload
		public Acknowledgement getLoanStatus (@RequestPayload CustomerRequest request) {
			return service.checkLoanEligiblity(request);
		}
}
