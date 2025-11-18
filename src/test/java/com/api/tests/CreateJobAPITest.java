	package com.api.tests;

import org.hamcrest.Matchers;
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
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {
	
	@Test  
	public void createJobAPITest() throws IOException
	{
		
		CustomerPOJO customer = new CustomerPOJO("Vivek", "Yadav", "8788138617", "", "ccivivek123@gmail.com", "");
		CustomerAddressPOJO customerAddress = new CustomerAddressPOJO("D 101", "Shri Maa", "Katraj", "katraj bypas", "katraj Pune", "412207", "India", "Maharashtra");
		CustomerProduct_POJO customerProduct = new CustomerProduct_POJO("2024-10-15T18:30:00.000Z", "701398385161723", "701398385161723", "701398385161723", "2024-10-15T18:30:00.000Z", 1, 2);
		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemslist = new ArrayList<Problems>();
		problemslist.add(problems);
		
		CreateJobAPI_POJO CreateJobAPI = new CreateJobAPI_POJO(0, 2, 1, 1, customer, customerAddress, customerProduct, problemslist);
		
		 given()
		.spec(SpecUtil.requestSpecWithAuthToken(Roles.FD, CreateJobAPI))
		
		.when()
		.post("job/create")
		
		.then()
		.log().all()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema_Folder/CreateJobAPIResponse.json"))
		.body("message",Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number",Matchers.startsWith("JOB_"));  
	}

}
