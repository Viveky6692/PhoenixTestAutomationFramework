package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.api.utils.Config_Manager;




public class DatabaseManager {
	
	private DatabaseManager() {
		
	}
	
	private static final String DB_URL= Config_Manager.getProperty("DB_URL");
	private static final String DB_USER_NAME= Config_Manager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD= Config_Manager.getProperty("DB_PASSWORD");
	
	    private volatile static Connection conn;  // volatile - Any update happens in conn variable then all threads
	    // will be aware of it 
	    
		public static void createConnection() throws SQLException, IOException
		{
			/*String DB_URL= Config_Manager.getProperty("DB_URL");
		    String DB_USER_NAME= Config_Manager.getProperty("DB_USER_NAME");
		    String DB_PASSWORD= Config_Manager.getProperty("DB_PASSWORD");
			conn= DriverManager.getConnection(DB_URL, DB_USER_NAME,DB_PASSWORD);*/
			
			if(conn==null)  // first check all parallel thread will enter 
			{
				synchronized (DatabaseManager.class)
				{
					if(conn==null)  // only and only for the first Connection Request
					{
						conn= DriverManager.getConnection(DB_URL, DB_USER_NAME,DB_PASSWORD);
						System.out.println(conn);
					}
				}
				
			}
		
		}

		
}
