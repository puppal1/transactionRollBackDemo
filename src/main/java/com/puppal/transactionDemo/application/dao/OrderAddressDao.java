package com.puppal.transactionDemo.application.dao;

import com.puppal.transactionDemo.application.exceptions.AddressPlatformException;
import com.puppal.transactionDemo.application.exceptions.OrderAddressDBException;
import com.puppal.transactionDemo.application.model.OrderModel;

public interface OrderAddressDao {

	
	public int addOrderAddressLine(OrderModel orderModel) throws OrderAddressDBException, AddressPlatformException ; 
}
