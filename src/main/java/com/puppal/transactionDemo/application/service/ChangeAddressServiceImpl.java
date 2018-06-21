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
import com.puppal.transactionDemo.application.dao.TxnStatusDao;
import com.puppal.transactionDemo.application.exceptions.AddressPlatformException;
import com.puppal.transactionDemo.application.model.AddressModel;
import com.puppal.transactionDemo.application.model.OrderModel;
import com.puppal.transactionDemo.application.model.TransactionStatusModel;

@Component
public class ChangeAddressServiceImpl implements ChangeOrderAddressService {

	private final static Logger logger = LoggerFactory.getLogger(ChangeAddressServiceImpl.class);

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private OrderAddressDao orderAddressDao;

	@Autowired
	NotifyAddressChangeServiceImpl notificationService;

	@Autowired
	private TxnStatusDao txnDao;

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ,
			// rollbackForClassName = {"WareHouseException.class",
			// "OrderAddressDBException.class" }
			rollbackFor = AddressPlatformException.class)
	@Override
	public String updateOrderAddress(AddressModel addressModel, OrderModel orderModel) throws AddressPlatformException {
		String transcationStatus = "FAILURE";
		try {
			addressModel = addressDao.addAddress(addressModel);
			logger.info(" Address ID gen: " + addressModel.getAddressId());
			boolean addressPersistStatus = addressModel.getAddressId() != null ? true : false;
			logger.info(" Address persist status: " + addressPersistStatus);
			orderModel.setAddressId(addressModel.getAddressId());
			int orderRetVal = orderAddressDao.addOrderAddressLine(orderModel);
			boolean orderPersistStatus = (orderRetVal <= 0 ? false : true);
			logger.info(" Order persis success: " + orderPersistStatus);
			notificationService.wareHouseAPI();
			transcationStatus = "SUCCESS";
			
		}

		finally {
			logger.info("Exitting update adress service ");
		}
		return transcationStatus;
	}

	public void updateTxnStatus(TransactionStatusModel model) {
		txnDao.addTxn(model);
	}

}
