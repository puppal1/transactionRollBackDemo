package com.puppal.transactionDemo.application.model;

import java.math.BigInteger;
import java.util.Date;

public class OrderModel {

	
	private BigInteger orderId;
	private BigInteger lineId;
	private BigInteger addressId;
	private Date createdDate;
	private Date modifiedDate;

	public BigInteger getOrderId() {
		return orderId;
	}

	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}

	public BigInteger getLineId() {
		return lineId;
	}

	public void setLineId(BigInteger lineId) {
		this.lineId = lineId;
	}

	public BigInteger getAddressId() {
		return addressId;
	}

	public void setAddressId(BigInteger addressId) {
		this.addressId = addressId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
