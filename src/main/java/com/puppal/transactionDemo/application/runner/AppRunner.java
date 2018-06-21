package com.puppal.transactionDemo.application.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.puppal.transactionDemo.application.model.AddressModel;
import com.puppal.transactionDemo.application.model.OrderModel;
import com.puppal.transactionDemo.application.model.TransactionStatusModel;
import com.puppal.transactionDemo.application.service.ChangeOrderAddressService;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AppRunner implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final ChangeOrderAddressService orderAddServ;

	public AppRunner(ChangeOrderAddressService orderAddServ) {
		this.orderAddServ = orderAddServ;
	}

	
	/**
	 * create sample objects to be persisted call order service to persist call
	 * txn status
	 */
	public void run(String... args) {
		TransactionStatusModel model = new TransactionStatusModel();
		AddressModel addressModel = popAddress();
		OrderModel orderModel = popOrder();
		model.setOrderId(orderModel.getOrderId().longValue());
		model.setUserId(addressModel.getUserId().longValue());
		try {
			String stat = orderAddServ.updateOrderAddress(addressModel, orderModel);
			model.setTxnStatus(stat);
			orderAddServ.updateTxnStatus(model);
		} catch (Exception e) {
			logger.info("Roll back reason: " + e.getMessage() + " ");
			model.setTxnStatus("FAILURE");
			model.setFailureReason(e.getMessage());
			orderAddServ.updateTxnStatus(model);
		} finally {
			logger.info("END OF PROGRAM");
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
		OrderModel model = new OrderModel();
		model.setOrderId(new BigInteger("123"));
		return model;
	}
}
