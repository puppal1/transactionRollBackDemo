package com.puppal.transactionDemo.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.puppal.transactionDemo.application.model.TransactionStatusModel;

@Configuration
@PropertySource(name = "abc", value = "classpath:dbQuery.properties")

@ConfigurationProperties(prefix = "query.txn")

public class TxnStatusDaoImpl implements TxnStatusDao {

	private final static Logger logger = LoggerFactory.getLogger(TxnStatusDaoImpl.class);
	// Query
	private String insert;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public TransactionStatusModel addTxn(TransactionStatusModel model) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {

			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(getInsert(), Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, model.getTxnStatus());
					ps.setString(2, model.getFailureReason());
					ps.setLong(3, model.getUserId());
					ps.setLong(4, model.getOrderId());
					return ps;
				}
			}, keyHolder);

		} finally {
			if (null != keyHolder.getKey()) {
				logger.info(" TXN ID: " + keyHolder.getKey().toString());
				model.setTxn_id(keyHolder.getKey().longValue());
			}
		}
		return model;
	}

	public String getInsert() {
		return insert;
	}

	public void setInsert(String insert) {
		this.insert = insert;
	}

}
