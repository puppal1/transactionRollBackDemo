package com.puppal.transactionDemo.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puppal.transactionDemo.application.dao.AddressDao;
import com.puppal.transactionDemo.application.dao.OrderAddressDao;
import com.puppal.transactionDemo.application.exceptions.AddressPlatformException;
import com.puppal.transactionDemo.application.model.AddressModel;
import com.puppal.transactionDemo.application.model.OrderModel;

@Component
public class ChangeAddressServiceImpl implements ChangeOrderAddressService {

	private final static Logger logger = LoggerFactory.getLogger(ChangeAddressServiceImpl.class);

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private OrderAddressDao orderAddressDao;

	@Autowired
	NotifyAddressChangeServiceImpl notificationService;
	
	
	

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, 
		//	rollbackForClassName = {"WareHouseException.class", "OrderAddressDBException.class" }
			rollbackFor = AddressPlatformException.class
	)
	@Override
	public void updateOrderAddress(AddressModel addressModel, OrderModel orderModel) throws AddressPlatformException{
		try {
			addressModel = addressDao.addAddress(addressModel);

			System.out.println(addressModel.getAddressId());
			orderModel.setAddressId(addressModel.getAddressId());
			System.out.println(orderAddressDao.addOrderAddressLine(orderModel));
			notificationService.wareHouseAPI();
		} 

		finally {
			logger.info("Exitting update adress service ");

		}

	}

}
