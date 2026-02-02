package com.database;

import java.io.IOException;
import java.sql.SQLException;

public class DemoRunner {

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		
		DatabaseManager.createConnection();
		long startTime = System.currentTimeMillis();
		
		for(int i=1; i<=1000; i++)
		{
			DatabaseManager.createConnection();
			DatabaseManager.createConnection();
			
			
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Duration "+ (endTime- startTime) + "ms");
	}

}
