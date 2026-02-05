package com.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.Config_Manager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Demo_CheckingOfHikariDBManager {

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = DatabaseManager.getConnection();  // getConnection is calling createConnection
		System.out.println(conn);
		
		
		
	}

}
