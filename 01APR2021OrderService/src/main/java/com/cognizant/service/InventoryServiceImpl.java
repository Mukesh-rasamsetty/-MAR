package com.cognizant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	@HystrixCommand(fallbackMethod = "isAvaliable_Fallback")
	public boolean isAvaliable(int long1) {
		Integer temp = restTemplate.getForObject("http://localhost:8082/inventory/checking/{id}", Integer.class, long1);
		if(temp >= 0)
			return true;
		return false;
	}
	
	public boolean isAvaliable_Fallback(Long l) {
		System.out.println("Inventory service is down...!");
		return false;
	}

}
