package com.springsoapBe.api.service;

import org.springframework.stereotype.Service;

import com.webserviceSoapbe.pojos.ExternalCallRequest;
import com.webserviceSoapbe.pojos.ExternalCallResponse;
import com.webserviceSoapbe.pojos.ServicePhase;
import com.webserviceSoapbe.pojos.Trigger;

@Service
public class BeServiceImpl{
	String BEentity;
	ServicePhase servicephase;
			// 1. Start with response object, so createa method that gives
			// response object
	
			public  ExternalCallResponse getBeValidation(ExternalCallRequest request ) {
				//2. get the instance of the request method 
				ExternalCallResponse response = new ExternalCallResponse();
				
				// here starts the busines logic on you transform the be request.
				
				Trigger trigger = new Trigger();
				
				 // get the phase where it has call was made.
				// As per definition, trigger object will give service phase and BeEntiy
				
				BEentity=trigger.getBusinessEntity();
				servicephase = trigger.getServicePhase();
			
				System.out.println("HERE!!");
				System.out.println(request.getServiceCall() );
				System.out.println("Trigger details" + trigger);
				return response;
				
			}
}
	

