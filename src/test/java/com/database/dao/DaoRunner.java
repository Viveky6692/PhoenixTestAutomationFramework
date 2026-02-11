package com.database.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;

import com.Test.pojo.Customer;
import com.database.model.CustomerDBModel;

public class DaoRunner {

	public static void main (String args []) throws SQLException, IOException
	{
		// Data fetched from Database
		CustomerDBModel customerDBData= CustomerDao.getCustomerInfo(178298);
		System.out.println(customerDBData.getFirst_name());
		System.out.println(customerDBData.getEmail_id());
		System.out.println(customerDBData.getMobile_number());
		
		// payload Data
		Customer customer = new Customer("Ferne", "Pagac", "559-964-0867", "", "Geovany_Orn@hotmail.com", "");
		System.out.println(customer.first_name());
		
		
		//Comparing Data of DB and Paylod
		Assert.assertEquals(customerDBData.getFirst_name(), customer.first_name());
	}
}
