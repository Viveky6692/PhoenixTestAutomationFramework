	package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Test.pojo.CreateJobAPI_POJO;
import com.Test.pojo.CustomerAddressPOJO;
import com.Test.pojo.CustomerPOJO;
import com.Test.pojo.CustomerProduct_POJO;
import com.Test.pojo.Problems;
import com.api.constant.Roles;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;
import com.database.dao.CustomerDao;
import com.database.model.CustomerDBModel;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIwithDBValidationTest {
	
	CustomerPOJO customer;  // request Body Data from CreateJob Payload
	
	@Test  
	public void createJobAPITest() throws IOException, SQLException
	{
		
		System.out.println(Instant.now());;
		customer = new CustomerPOJO("Vivek", "Yadav", "8788138617", "", "ccivivek123@gmail.com", "");
		CustomerAddressPOJO customerAddress = new CustomerAddressPOJO("D 101", "Shri Maa", "Katraj", "katraj bypas", "katraj Pune", "412207", "India", "Maharashtra");
		CustomerProduct_POJO customerProduct = new CustomerProduct_POJO(DateTimeUtil.getTimeWithDaysAgo(10), "129398095850789", "129398095850789", "129398095850789", DateTimeUtil.getTimeWithDaysAgo(10), 1, 2);
		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemslist = new ArrayList<Problems>();
		problemslist.add(problems);
		
		CreateJobAPI_POJO CreateJobAPI = new CreateJobAPI_POJO(0, 2, 1, 1, customer, customerAddress, customerProduct, problemslist);
		
		int customerId= given()
		.spec(SpecUtil.requestSpecWithAuthToken(Roles.FD, CreateJobAPI))
		
		.when()
		.post("job/create")
		
		.then()
		.log().all()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema_Folder/CreateJobAPIResponse.json"))
		.body("message",Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number",Matchers.startsWith("JOB_"))
		.extract().body().jsonPath().getInt("data.tr_customer_id");
		
		System.out.println(customerId);
		
		
		// DB Data 
		CustomerDBModel customerDataFromDB=   CustomerDao.getCustomerInfo(customerId); 
		System.out.println(customerDataFromDB);
		
		// Assert that values from Customer (Payload Data) and customerDataFromDB are same
		
		Assert.assertEquals(customerDataFromDB.getFirst_name(), customer.first_name());
		Assert.assertEquals(customerDataFromDB.getLast_name(), customer.last_name());
		Assert.assertEquals(customerDataFromDB.getMobile_number(), customer.mobile_number());
		Assert.assertEquals(customerDataFromDB.getMobile_number_alt(), customer.mobile_number_alt());
		Assert.assertEquals(customerDataFromDB.getEmail_id(), customer.email_id());
		Assert.assertEquals(customerDataFromDB.getEmail_id_alt(), customer.email_id_alt());
		
		
				
	}
	
	

}
