package com.puppal.transactionDemo.application.service;

import com.puppal.transactionDemo.application.model.AddressModel;
import com.puppal.transactionDemo.application.model.OrderModel;

public interface ChangeOrderAddressService {

	public void updateOrderAddress (AddressModel addressModel , OrderModel orderModel) throws Exception  ;
	
}
