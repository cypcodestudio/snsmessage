package com.cypcode.snsmessage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cypcode.snsmessage.dto.SMSRequestDTO;
import com.cypcode.snsmessage.service.AWSSNSService;

@RestController
@RequestMapping("notification")
public class AWSSNSController {
	
	@Autowired
	AWSSNSService snsService;
	
	@PostMapping("subscribe/{mobile}")
	public String sendSMS(@PathVariable(value = "mobile")String mobile) {
		String response = snsService.subscribeMobile(mobile);
		return String.format("SNS Response: %s", response);
	}
	
	@PostMapping("customer-sms")
	public String sendCustomerSMS(@RequestBody SMSRequestDTO request) {
		String response = snsService.publishMessageToSNSTopic(request.getMessage(), request.getMobile());
		return String.format("SNS Response: %s", response);
	}
	
	@PostMapping("campaign-sms")
	public String sendCampaignSMS(@RequestBody SMSRequestDTO request) {
		String response = snsService.publishMessageToSNSTopic(request.getMessage());
		return String.format("SNS Response: %s", response);
	}

}
