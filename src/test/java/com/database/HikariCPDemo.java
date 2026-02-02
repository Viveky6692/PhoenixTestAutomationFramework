package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.Config_Manager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDemo {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		HikariConfig hikariConfig = new HikariConfig();
		
		hikariConfig.setJdbcUrl(Config_Manager.getProperty("DB_URL"));
		hikariConfig.setUsername(Config_Manager.getProperty("DB_USER_NAME"));
		hikariConfig.setPassword(Config_Manager.getProperty("DB_PASSWORD"));
		hikariConfig.setMaximumPoolSize(10); // 10 DB Connections 
		hikariConfig.setMinimumIdle(2);  // 2 DB connections remain idle out of total 10 DB Connections
		hikariConfig.setConnectionTimeout(10000); // 10 seconds = 10 * 1000 ms = 10000
		hikariConfig.setIdleTimeout(10000);  // close all idle DB Connections if they remain inactive for more than 10 seconds 
		
		hikariConfig.setMaxLifetime(1800000);  // 30 mins = 30 * 60 * 1000 // Close all old connections and create new DB connections 
		hikariConfig.setPoolName("Phoenix Test Automation Framework Pool");
		
		HikariDataSource ds = new HikariDataSource(hikariConfig);
		Connection conn = ds.getConnection();
		
		System.out.println(conn);
		
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery("Select first_name, last_name, mobile_number from tr_customer;");
		
		while(resultSet.next())
		{
		// resultSet.getString("first_name" +resultSet.getString("last_name ") +resultSet.getString("mobile_number"));
			
			System.out.println( resultSet.getString("first_name") + ": "+ resultSet.getString("last_name") + ": " + resultSet.getString("mobile_number") );
			
		}
		
		ds.close();
		
	}

}
