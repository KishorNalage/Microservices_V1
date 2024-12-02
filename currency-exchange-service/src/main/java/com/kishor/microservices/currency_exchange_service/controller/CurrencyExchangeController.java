package com.kishor.microservices.currency_exchange_service.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kishor.microservices.currency_exchange_service.bean.CurrencyExchange;
import com.kishor.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveCurrency(@PathVariable String from,
			@PathVariable String to)
	{
		//CurrencyExchange currencyExchange = new CurrencyExchange(100L, "USD", "INR", BigDecimal.valueOf(50));
		CurrencyExchange currencyExchange = (CurrencyExchange) currencyExchangeRepository.findByFromAndTo(from,to);
		if(currencyExchange ==null) {
			throw new RuntimeException
				("Unable to Find data for " + from + " to " + to);
		}
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
		
	}

}
