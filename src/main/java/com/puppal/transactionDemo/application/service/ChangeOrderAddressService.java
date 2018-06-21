package com.puppal.transactionDemo.application.service;

import com.puppal.transactionDemo.application.model.AddressModel;
import com.puppal.transactionDemo.application.model.OrderModel;
import com.puppal.transactionDemo.application.model.TransactionStatusModel;

public interface ChangeOrderAddressService {

	public String updateOrderAddress (AddressModel addressModel , OrderModel orderModel) throws Exception  ;
	public void updateTxnStatus(TransactionStatusModel model) ;
	
	
}
