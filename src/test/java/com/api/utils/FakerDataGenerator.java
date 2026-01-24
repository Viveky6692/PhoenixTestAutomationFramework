package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.Test.pojo.CreateJobPayload;
import com.Test.pojo.Customer;
import com.Test.pojo.CustomerAddress;
import com.Test.pojo.CustomerProduct;
import com.Test.pojo.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {
	
	private static Faker faker = new Faker(new Locale("en-IND")); //Help me to Create India specific fake Data
	final static String COUNTRY = "India";
	private final static Random RANDOM = new Random();
	private static final int MST_SERVICE_LOCATION_ID=0;
	private static final int MST_PLATFORM_ID=2;
	private static final int MST_WARRANTY_STATUS_ID=1;
	private static final int MST_OEM_ID=1;
	private static final int PRODUCT_ID=1;
	private static final int MST_MODEL_ID=1;
	private final static int validProblemsId[]= {1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,20,22,24,26,27,28,29};
	
	private void FakerDataGenerator()
	{
		
	}
	

	
	public static CreateJobPayload generateFakeCreateJobData()
	{
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddressData();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList = generateFakeCustomerList();
		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
		return	payload;
	}

	
	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count )
	{
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		
		for(int i=1;i<=count;i++) {
		
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddressData();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList = generateFakeCustomerList();
		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID, MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
		payloadList.add(payload);
	}
		
		return	payloadList.iterator();
	}



	private static List<Problems> generateFakeCustomerList() {
		
		
		int count = RANDOM.nextInt(3)+1;
		String fakeRemark ;
		int randomIndex ;
		Problems problems;
		List<Problems> problemList = new ArrayList<Problems>();
		
		
		
		for(int i=1;i<=count;i++)
		{
			// generating Random Problem ID and adding it to the List 
			
			
			 randomIndex = RANDOM.nextInt(validProblemsId.length);
			 fakeRemark = faker.lorem().sentence(4);
			 problems = new Problems(validProblemsId[randomIndex],fakeRemark);
			 problemList.add(problems);
						
		}
		
		return problemList;
	}



	private static CustomerProduct generateFakeCustomerProductData() {
		
		
		String dop= DateTimeUtil.getTimeWithDaysAgo(10);
		 String serial_number= faker.numerify("###############");	
		 String popurl= faker.internet().url();
		 
	
		CustomerProduct customerProduct = new CustomerProduct(dop, serial_number, serial_number, serial_number, popurl, PRODUCT_ID, MST_MODEL_ID);
		//System.out.println(customerProduct);
		return customerProduct;
	}



	private static CustomerAddress generateFakeCustomerAddressData() {
		
		//CustomerAddress Fake object Creation
		 String flat_number = faker.numerify("###");
		 String apartment_name = faker.address().streetName();
		 String street_name =  faker.address().streetName();
		 String landmark=  faker.address().streetName();
		 String area = faker.address().streetName();
		 String pincode = faker.numerify("######");
		 String state = faker.address().state();
		 
		 
		
		CustomerAddress customerAddress = new CustomerAddress(flat_number, apartment_name, street_name, landmark, area, pincode, COUNTRY, state);
	//System.out.println(customerAddress);
		return customerAddress;
	}



	private static Customer generateFakeCustomerData() {
		// TODO Auto-generated method stub
		
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String MobileNumber = faker.numerify("98########");
		String alternateMobileNumber = faker.numerify("7########");
		String emaild = faker.internet().emailAddress();
		//String alternateemaild = faker.internet().emailAddress();
		
		Customer customer = new Customer(fname, lname, MobileNumber, alternateMobileNumber, emaild, emaild);
		//System.out.println(customer);
		
		return customer;
		
	}

}
