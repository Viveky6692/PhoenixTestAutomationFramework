package com.api.tests;

import org.testng.annotations.Test;

import com.Test.pojo.Customer;
import com.Test.pojo.CustomerAddress;
import com.Test.pojo.CustomerProduct;
import com.Test.pojo.Problems;
import com.api.request.model.CreateJobPayload;
import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

import static  io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CreateJobAPITest {
	
	
	
	
	@Test
	public void CreateJobAPITest() throws IOException
	{
		// Creating CreateJobPayload Object
		
		Customer customer = new Customer("Vivek","Yadav","8788138617","", "test@yopmail.com","");
		
		
		CustomerAddress customer_address = new CustomerAddress("B101", "Shree radhe", "Street", "landmark", "Mumbai", "412207", "India", "Maharashtra");
		//CustomerAddress customerAddress = new CustomerAddress(sessionId, rootPath, baseURI, basePath, DEFAULT_URI, DEFAULT_SESSION_ID_VALUE, DEFAULT_PATH, DEFAULT_BODY_ROOT_PATH)
		CustomerProduct customerProduct = new CustomerProduct("2024-10-15T18:30:00.000Z", "15345678991273", "15345678991273","15345678991273", "2024-10-15T18:30:00.000Z", 1, 1);   
		
		Problems problems = new Problems(1,"battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customer_address, customerProduct, problemList);
		
		
		given()
		.spec(SpecUtil.requestSpecWithAuthToken(Roles.FD, createJobPayload))
		
		.when()
		.post("/job/create")
		
		.then()
		.spec(SpecUtil.responseSpecification());
	//	.log().all()
		//.statusCode(200);
		
		
	}

}
