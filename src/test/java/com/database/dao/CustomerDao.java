package com.database.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	
	// executing the Query for table tr_Customer. Which will fetch the details of the customer
	
	private static final String CUSTOMER_DETAIL_QUERY = 
			"""
			select * from tr_customer  where id = ?
			""";
	/*
	 * last_name
first_name
mobile_number
mobile_number_alt
email_id
email_id_alt
	 * 
	 */
	public static CustomerDBModel getCustomerInfo(int customer_id) throws SQLException, IOException
	{
		Connection conn= DatabaseManager.getConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(CUSTOMER_DETAIL_QUERY);
		preparedStatement.setInt(1, customer_id);
		ResultSet resultSet= preparedStatement.executeQuery();
		CustomerDBModel customerDBModel=null;	
		
			
			while(resultSet.next()) {
				
				customerDBModel = new CustomerDBModel(
				resultSet.getString("first_name"),
				resultSet.getString("last_name"),
				resultSet.getString("mobile_number"),
				resultSet.getString("mobile_number_alt"),
				resultSet.getString("email_id"),
				resultSet.getString("email_id_alt")
				);
				
				
				System.out.println(resultSet.getString("first_name"));
				System.out.println(resultSet.getString("last_name"));
				System.out.println(resultSet.getString("mobile_number"));
				System.out.println(resultSet.getString("email_id"));
				
									
		}
	
		return customerDBModel;
	}
	
}
