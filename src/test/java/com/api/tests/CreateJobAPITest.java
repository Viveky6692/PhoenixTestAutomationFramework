	package com.api.tests;

import org.testng.annotations.Test;

import com.Test.pojo.CreateJobAPI_POJO;
import com.Test.pojo.CustomerAddressPOJO;
import com.Test.pojo.CustomerPOJO;
import com.Test.pojo.CustomerProduct_POJO;
import com.Test.pojo.Problems;
import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.Config_Manager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class CreateJobAPITest {
	
	@Test  
	public void createJobAPITest() throws IOException
	{
		
		CustomerPOJO customer = new CustomerPOJO("Vivek", "Yadav", "8788138617", "", "ccivivek123@gmail.com", "");
		CustomerAddressPOJO customerAddress = new CustomerAddressPOJO("D 101", "Shri Maa", "Katraj", "katraj bypas", "katraj Pune", "412207", "India", "Maharashtra");
		CustomerProduct_POJO customerProduct = new CustomerProduct_POJO("2024-10-15T18:30:00.000Z", "98131415161718", "98131415161718", "98131415161718", "2024-10-15T18:30:00.000Z", 1, 2);
		Problems problems = new Problems(1, "Battery Issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0] = problems;
		
		CreateJobAPI_POJO CreateJobAPI = new CreateJobAPI_POJO(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		 given()
		.spec(SpecUtil.requestSpecWithAuthToken(Roles.FD, CreateJobAPI))
		
		.when()
		.post("job/create")
		
		.then()
		.log().all()
		.statusCode(200);
		
		
	}

}
