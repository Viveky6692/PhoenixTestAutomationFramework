package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.api.utils.Config_Manager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;




public class DatabaseManager {   // Singleton Class - Only 1 object is created of the class
	
	private DatabaseManager() {
		
	}
	
	
	private static final String DB_URL= Config_Manager.getProperty("DB_URL");
	private static final String DB_USER_NAME= Config_Manager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD= Config_Manager.getProperty("DB_PASSWORD");
//	private static  HikariConfig hikariConfig;
	private volatile static HikariDataSource hikariDataSource;
	
	private static final int MAXIMUM_POOL_SIZE= Integer.parseInt(Config_Manager.getProperty("MAXIMUM_POOL_SIZE"));
	private static final int MINIMUM_IDLE_COUNT = Integer.parseInt(Config_Manager.getProperty("MINIMUM_IDLE_COUNT"));
	private static final int CONNECTION_TIMEOUT_IN_SECS = Integer.parseInt(Config_Manager.getProperty("CONNECTION_TIMEOUT_IN_SECS"));
	private static final int IDLE_TIMEOUT_SECS = Integer.parseInt(Config_Manager.getProperty("IDLE_TIMEOUT_SECS"));
	private static final int MAX_LIFE_TIME_IN_MINS = Integer.parseInt(Config_Manager.getProperty("MAX_LIFE_TIME_IN_MINS"));
	
	private static final String HIKARI_CP_POOL_NAME = (Config_Manager.getProperty("HIKARI_CP_POOL_NAME"));
	
	
	
	
	
	 //   private volatile static Connection conn;  // volatile - Any update happens in conn variable then all threads
	    // will be aware of it 
	    
		private static void initializePool() throws SQLException, IOException
		{
			
			
			if(hikariDataSource==null)  // first check all parallel thread will enter 
			{
				synchronized (DatabaseManager.class)
				{
					if(hikariDataSource==null)  // only and only for the first Connection Request
					{
						HikariConfig hikariConfig = new HikariConfig();
						hikariConfig.setJdbcUrl(DB_URL);
						hikariConfig.setUsername(DB_USER_NAME);
						hikariConfig.setPassword(DB_PASSWORD);
						hikariConfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE); // 10 DB Connections 
						hikariConfig.setMinimumIdle(MINIMUM_IDLE_COUNT);  // 2 DB connections remain idle out of total 10 DB Connections
						hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SECS *1000); // 10 seconds = 10 * 1000 ms = 10000
						hikariConfig.setIdleTimeout(IDLE_TIMEOUT_SECS*1000);  // close all idle DB Connections if they remain inactive for more than 10 seconds 
						
						hikariConfig.setMaxLifetime(MAX_LIFE_TIME_IN_MINS*60*1000);  // 30 mins = 30 * 60 * 1000 // Close all old connections and create new DB connections 
						hikariConfig.setPoolName(HIKARI_CP_POOL_NAME);
						
						hikariDataSource  = new HikariDataSource(hikariConfig);
						
					
					}
				}
				
			}
					
		}

		public static Connection getConnection() throws SQLException, IOException
		{
			if(hikariDataSource==null)
			{
				initializePool(); // Automatic initialization of Hikari DataSource
			}
			
			else if(hikariDataSource.isClosed())   
			{
				throw new SQLException("Hikari Data Source is Closed");
			}
			
			Connection connection = hikariDataSource.getConnection();		
			return connection;
		}
		
}
