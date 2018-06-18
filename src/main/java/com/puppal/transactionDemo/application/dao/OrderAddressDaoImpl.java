package com.puppal.transactionDemo.application.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.puppal.transactionDemo.application.exceptions.AddressPlatformException;
import com.puppal.transactionDemo.application.exceptions.OrderAddressDBException;
import com.puppal.transactionDemo.application.model.OrderModel;

@Configuration
@PropertySource(name = "abc", value = "classpath:dbQuery.properties")

@ConfigurationProperties(prefix = "query.order")
public class OrderAddressDaoImpl implements OrderAddressDao {

	private String orderAddressInsert;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int addOrderAddressLine(OrderModel orderModel) throws OrderAddressDBException{
		int retVal = 0;
		try {
			retVal = jdbcTemplate.update(getOrderAddressInsert(),
					new Object[] { orderModel.getOrderId(), orderModel.getAddressId() });
		} finally {
			
//			if(retVal==1)
//			throw new OrderAddressDBException(" DB error ");
			
		}
		return retVal;
	}

	public String getOrderAddressInsert() {
		return orderAddressInsert;
	}

	public void setOrderAddressInsert(String orderAddressInsert) {
		this.orderAddressInsert = orderAddressInsert;
	}

}
