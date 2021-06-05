package com.springsoapBe.api.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.informatica.mdm.spi.externalcall.ExternalCallResponse;
import com.springsoapBe.api.service.CustomerService;
import com.webserviceSoapbe.pojos2.Customer;
import com.webserviceSoapbe.pojos2.CustomerResponse;

/**
 * 
 * @author Yuvi - simple endpoint using the xsd created.
 */

@Endpoint
public class CustomerEndpoint {

	private static final String NAMESPACE = "urn:bes-external-call.informatica.mdm";

	@Autowired
	private CustomerService customerService;

	@PayloadRoot(namespace = NAMESPACE, localPart = "Customer")
	@ResponsePayload
	public CustomerResponse getCustomerResponse(@RequestPayload Customer request) {
		
				 customerService.customerValidation(request);
				 return new CustomerResponse();
	}
}
