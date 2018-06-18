package com.puppal.transactionDemo.application.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.puppal.transactionDemo.application.exceptions.WareHouseException;

@Component
public class NotifyAddressChangeServiceImpl {

	public int wareHouseAPI() throws WareHouseException {
		int sts = callWareHouseAPI();
		if (sts != 200)
			throw new WareHouseException(" Server not available");
		else
			return sts;
	}

	private int callWareHouseAPI() {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			String fooResourceUrl

					= "http://localhost:8080/spring-rest/foos";
			restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response != null ? response.getStatusCodeValue() : 0;

	}

}
