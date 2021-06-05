package com.springsoapBe.api.service;

import com.informatica.mdm.spi.externalcall.ExternalCallResponse;
import com.springsoapBe.api.service.BEUtil.EmptyLogic;
import com.webserviceSoapbe.pojos2.CustomerResponse;
import com.webserviceSoapbe.pojos2.ServiceCall;
import com.webserviceSoapbe.pojos2.ServiceError;

public class CustomizedRepsonse extends CustomerResponse {
	//set the service Call
	// set the SerivceError
	// -- not working
	
	// trying to create SDO
	
	
	public CustomerResponse  createResponse(String objectXML) {
		
		CustomerResponse response = new CustomerResponse();
		
		
		ServiceCall serviceCall = new ServiceCall();
		ServiceError serviceError =new ServiceError();
		
		serviceCall.setObjectXml(objectXML);
		serviceError.setCode(null);
		serviceError.setMessage("YOu are awesome");
		//serviceError.setDetailsXml(objectXML);
		
		response.setServiceCall(serviceCall);
		response.setServiceError(serviceError);
		return response;

	}
	
	
	
	
	
		
}
