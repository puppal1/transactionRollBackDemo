package com.puppal.transactionDemo.application.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.puppal.transactionDemo.application.model.AddressModel;
import com.puppal.transactionDemo.application.model.OrderModel;
import com.puppal.transactionDemo.application.service.ChangeOrderAddressService;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AppRunner implements CommandLineRunner{

	private final static Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final ChangeOrderAddressService orderAddServ;

	public AppRunner(ChangeOrderAddressService orderAddServ) {
		this.orderAddServ = orderAddServ;
	}

	public void run(String... args) {
		
		try {
			orderAddServ.updateOrderAddress(popAddress(), popOrder());
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Roll back reason: " + e.getMessage() +" ");
		}
		
		
	}

	public AddressModel popAddress() {
		AddressModel addressModel = new AddressModel();
		addressModel.setAddressLine1("234");
		addressModel.setFirstName("Don");
		addressModel.setLastName("up");
		addressModel.setUserId(new BigInteger("12"));
		addressModel.setState("CA");
		addressModel.setCountry("USA");
		return addressModel;
	}

	public OrderModel popOrder() {
    	OrderModel model =  new OrderModel();
    			model.setAddressId(new BigInteger("12"));
    	model.setOrderId(new BigInteger("123"));
    			return model;
    }
}
