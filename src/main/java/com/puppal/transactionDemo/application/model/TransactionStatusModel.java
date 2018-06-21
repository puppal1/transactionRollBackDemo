package com.puppal.transactionDemo.application.model;

import java.math.BigInteger;
import java.util.Date;

public class TransactionStatusModel {

	private long txn_id;
	private Date createTime;
	private String txnStatus;
	private String failureReason;
	private long orderId;
	private long userId;
	
	

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTxn_id() {
		return txn_id;
	}

	public void setTxn_id(long txn_id) {
		this.txn_id = txn_id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

}
