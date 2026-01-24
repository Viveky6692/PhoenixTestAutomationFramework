	package com.api.tests;

import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Test.pojo.CreateJobAPI_POJO;
import com.Test.pojo.CreateJobPayload;
import com.Test.pojo.Customer;
import com.Test.pojo.CustomerAddress;
import com.Test.pojo.CustomerAddressPOJO;
import com.Test.pojo.CustomerPOJO;
import com.Test.pojo.CustomerProduct;
import com.Test.pojo.CustomerProduct_POJO;
import com.Test.pojo.Problems;
import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.Config_Manager;
import com.api.utils.DateTimeUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtil;
import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CreateJobAPITestwithFakeData {
	
	private  CreateJobPayload createJobPayload;
	
	private  CreateJobAPITestwithFakeData()
	{
				
	}
	
	@BeforeMethod(description  =" Creating createJob API payload")
	public void setup()
	{
		createJobPayload = FakerDataGenerator.generateFakeCreateJobData();
	}
	
	@Test  
	public void createJobAPITest() throws IOException
	{
			
		
		 given()
		.spec(SpecUtil.requestSpecWithAuthToken(Roles.FD, createJobPayload))
		
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
