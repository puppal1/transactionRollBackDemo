package com.puppal.transactionDemo.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.puppal.transactionDemo.application.exceptions.WareHouseException;

@Component
public class NotifyAddressChangeServiceImpl {

	private final static Logger logger = LoggerFactory.getLogger(NotifyAddressChangeServiceImpl.class);

	public int wareHouseAPI() throws WareHouseException {
		int sts = callWareHouseAPI();
		if (sts != 200)
			throw new WareHouseException(" Server not available http status code : " +  sts);
		else
			return sts;
	}
	

	private int callWareHouseAPI() throws WareHouseException {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			String fooResourceUrl

					= "http://localhost:8080/spring-rest/foos";
			restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new WareHouseException(e.getMessage());
		}
		return response != null ? response.getStatusCodeValue() : 0;

	}

}
