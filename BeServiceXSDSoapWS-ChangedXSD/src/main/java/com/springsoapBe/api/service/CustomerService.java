package com.springsoapBe.api.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.informatica.mdm.spi.cs.StepException;
import com.informatica.mdm.spi.externalcall.CustomLogic;
import com.informatica.mdm.spi.externalcall.ExternalCallResponse;
import com.springsoapBe.api.service.BEUtil.EmptyLogic;
import com.sun.xml.messaging.saaj.packaging.mime.Header;
import com.webserviceSoapbe.pojos2.Customer;

import com.webserviceSoapbe.pojos2.CustomerResponse;
import com.webserviceSoapbe.pojos2.Parameter;
import com.webserviceSoapbe.pojos2.ServiceCall;
import com.webserviceSoapbe.pojos2.Trigger;

import commonj.sdo.DataObject;
import commonj.sdo.helper.HelperContext;

/**
 * 
 * @author Yuvi simple service for BeCustomer - it does nothing but
 */

@Service
public class CustomerService {
	String businessEntity;

	// trying to return a empty logic object

	private static final CustomLogic EMPTY_LOGIC = new EmptyLogic();

	public CustomerResponse customerValidation(Customer request) {
		CustomerResponse response = new CustomerResponse();
		//Customized CustomerReponse
		
		CustomizedRepsonse customizedResponse = new CustomizedRepsonse();
		
		// process the received input
		Trigger trigger = request.getTrigger();
		businessEntity = trigger.getBusinessEntity(); // check the business logic.
		System.out.println("Hay!!!");
		System.out.println(businessEntity);
		System.out.println(trigger.getServicePhase());

		// serivce errors
		// service call

		ServiceCall servicecall = request.getServiceCall();
		com.webserviceSoapbe.pojos2.Header header = request.getHeader();

		System.out.println(header.getOrsId());
		System.out.println(header.getUser());
		System.out.println(header.getPassword());

		System.out.println(servicecall.getObjectXml());
		System.out.println(servicecall.getParameter());
		
		//set the retrun para from here 
		
		response = customizedResponse.createResponse(servicecall.getObjectXml()); // sending the inputxml as is.
		

		// print params
		System.out.println("Param");

		List<Parameter> parameter = servicecall.getParameter();

		for (Parameter param : parameter) {
			System.out.println("Param print xmlvalue");
			System.out.println(param.getXmlValue());
		}

		System.out.println(servicecall.getObjectXml());

		System.out.println("=====FINAL===SEND===");
		System.out.println(response.getServiceCall().getObjectXml());
		System.out.println(response.getServiceError().getMessage());
		return null;
	}

}
