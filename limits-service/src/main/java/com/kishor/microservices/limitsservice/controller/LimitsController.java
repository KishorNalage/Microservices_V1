package com.kishor.microservices.limitsservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kishor.microservices.limitsservice.Configuration.Configuration;
import com.kishor.microservices.limitsservice.bean.Limit;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public Limit retriveLimits() {
		return new Limit(configuration.getMinimum(),configuration.getMaximum());

	}

}
