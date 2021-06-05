package com.springsoapBe.api.service;

import org.springframework.stereotype.Service;

import com.webserviceSoapbe.pojos.CustomerRequest;
import com.webserviceSoapbe.pojos.CustomerResponse;
import com.webserviceSoapbe.pojos.ExternalCallRequest;
import com.webserviceSoapbe.pojos.ExternalCallResponse;
import com.webserviceSoapbe.pojos.Trigger;

@Service
public class CustomerValidation {
 String beEntity ;
	
	// responde with Request Object
	public CustomerResponse DoCustomerValidation(CustomerRequest request) {
		CustomerResponse response = new CustomerResponse();
		ExternalCallRequest berequest = request.getCustomerRequest();// externalRequest object
		Trigger trigger= berequest.getTrigger(); // trigger holds all details of routing
		
			beEntity=	trigger.getBusinessEntity(); // this gives which business entity
			
			//routing logic
			if (beEntity.equals("Person")) {//write your entity name here!!
				System.out.println("You are in the Person entity");
			} else {
				System.out.println(" You're in not in Person ");
				System.out.println(trigger.getBusinessEntity().toString());
				System.out.println(berequest.getServiceCall().getObjectXml());
			}

			//set the response object here next!!
//			ExternalCallResponse externalCallResponse = new ExternalCallResponse();
//			
//			externalCallResponse.setServiceCall(null);
//			response.setCustomerReponse();
		return 	null;
	}

}
