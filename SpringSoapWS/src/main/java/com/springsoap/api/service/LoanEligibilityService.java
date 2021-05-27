package com.springsoap.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webserviceSoap.pojos.Acknowledgement;
import com.webserviceSoap.pojos.CustomerRequest;

//business Logic
@Service
public class LoanEligibilityService {

		public Acknowledgement checkLoanEligiblity(CustomerRequest request) {
			Acknowledgement ack = new Acknowledgement();
			List<String> eligibitly= ack.getCriteriaMismatch(); //get the eligiblity
			
			
			
			//logic
			 
			if (!(request.getAge()>30 && request.getAge()<60)) {
				eligibitly.add("Person should be between 30 && 60 ");
			}
 			
			if (!(request.getYearlyIncome()>200000)) {
				eligibitly.add("Person should have more than 2000000 ");
			}
			
			if (!(request.getCibilScore()>500)) {
				eligibitly.add("Check back in 6 moths ");
			}
			
			
			if (eligibitly.size()>0) {
				ack.setApprovedAmount(0);
				ack.setIsEligible(false);
				
			}
			
			else  {
				ack.setApprovedAmount(50000);
				ack.setIsEligible(true);
				eligibitly.clear();
			}
			
			return ack;//returns the ack
			
			
		}
}
