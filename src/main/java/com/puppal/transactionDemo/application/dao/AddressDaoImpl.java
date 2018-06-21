package com.puppal.transactionDemo.application.dao;

import java.math.BigInteger;
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

import com.puppal.transactionDemo.application.model.AddressModel;

@Configuration
@PropertySource(name = "abc", value = "classpath:dbQuery.properties")


@ConfigurationProperties(prefix = "query")

public class AddressDaoImpl implements AddressDao {

	private final static Logger logger = LoggerFactory.getLogger(AddressDaoImpl.class);
	// Query
	private String addressInsert;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public AddressModel addAddress(AddressModel model) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {

			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(getAddressInsert(),
							Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, model.getUserId() != null ? model.getUserId().longValue() : new Long("0"));
					ps.setString(2, model.getFirstName());
					ps.setString(3, model.getLastName());
					ps.setString(4, model.getAddressLine1() != null ? model.getAddressLine1() : "");
					ps.setString(5, model.getAddressLine2());
					ps.setString(6, model.getCity());
					ps.setString(7, model.getState() != null ? model.getState() : "");
					ps.setString(8, model.getZip());
					ps.setString(9, model.getCountry() != null ? model.getCountry() : "US");
					ps.setLong(10, model.getCreatedBy() != null ? model.getCreatedBy().longValue() : new Long("0"));
					ps.setLong(11, model.getModifiedBy() != null ? model.getModifiedBy().longValue() : new Long("0"));
					return ps;
				}
			}, keyHolder);

		} finally {
			if (null != keyHolder.getKey()) {
				logger.info(" Address ID: " + keyHolder.getKey().toString());
				model.setAddressId(new BigInteger(keyHolder.getKey().toString()));
			}
		}
		return model;
	}

	public String getAddressInsert() {
		return addressInsert;
	}

	public void setAddressInsert(String addressInsert) {
		this.addressInsert = addressInsert;
	}

}
