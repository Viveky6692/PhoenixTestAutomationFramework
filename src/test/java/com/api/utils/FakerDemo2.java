package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.Test.pojo.CreateJobPayload;
import com.Test.pojo.Customer;
import com.Test.pojo.CustomerAddress;
import com.Test.pojo.CustomerAddressPOJO;
import com.Test.pojo.CustomerProduct;
import com.Test.pojo.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	// Create Fake CreateJobAPI Request payload 
	// I want to create a Fake Customer Object.
		
		final String COUNTRY = "India";
		
		Faker faker = new Faker(new Locale("en-IND")); //Help me to Create India specific fake Data
		
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String MobileNumber = faker.numerify("98########");
		String alternateMobileNumber = faker.numerify("7########");
		String emaild = faker.internet().emailAddress();
		//String alternateemaild = faker.internet().emailAddress();
		
		Customer customer = new Customer(fname, lname, MobileNumber, alternateMobileNumber, emaild, emaild);
		System.out.println(customer);
		
		//CustomerAddress Fake object Creation
		 String flat_number = faker.numerify("###");
		 String apartment_name = faker.address().streetName();
		 String street_name =  faker.address().streetName();
		 String landmark=  faker.address().streetName();
		 String area = faker.address().streetName();
		 String pincode = faker.numerify("######");
		 String state = faker.address().state();
		 
		 
		
		CustomerAddress customerAddress = new CustomerAddress(flat_number, apartment_name, street_name, landmark, area, pincode, COUNTRY, state);
		System.out.println(customerAddress);
		
		
		// Customer Product Fake object Creation
		
		 String dop= DateTimeUtil.getTimeWithDaysAgo(10);
		 String serial_number= faker.numerify("###############");	
		 String popurl= faker.internet().url();
		 
	
		CustomerProduct customerProduct = new CustomerProduct(dop, serial_number, serial_number, serial_number, popurl, 1, 1);
		System.out.println(customerProduct);
		
		String fakeRemark = faker.lorem().sentence(10);
		Random random = new Random();
		int problemId = random.nextInt(26)+1;
		
		Problems problems = new Problems(problemId,fakeRemark);
		
		System.out.println(problems);
		
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload payload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
	
		 System.out.println(payload);
	}

}
