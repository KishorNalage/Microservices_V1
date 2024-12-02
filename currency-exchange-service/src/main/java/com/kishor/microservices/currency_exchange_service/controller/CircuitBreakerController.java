package com.kishor.microservices.currency_exchange_service.controller;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger= LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping(path = "/sample-api")
	@Retry(name = "sample-api",fallbackMethod = "hardCodedReponse" )
	public String sampleApi() {
		logger.info("sample API call received");
		ResponseEntity<String> forEntity=new RestTemplate().getForEntity("localhost:8080/some-dummy-url", String.class);
		return forEntity.getBody();
	}
	
	public String hardCodedReponse(Exception ex)
	{
		return "fallback-response";
	}

}
