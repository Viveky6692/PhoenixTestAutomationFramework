package com.api.tests.datadriven;

import org.testng.annotations.Test;

import com.Test.pojo.Customer;
import com.Test.pojo.CustomerAddress;
import com.Test.pojo.CustomerProduct;
import com.Test.pojo.Problems;
import com.Test.pojo.CreateJobPayload;
import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

import static  io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CreateJobJsonDataDrivenTest {
	
	
	
	
	@Test(description = " Copy Data from csv and convert to Payload ", groups = {"Regression","Smoke","Data Driven Testing"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider = "CreateJobAPIJsonDataProvider"
	)
	
	public void CreateJobAPITest(CreateJobPayload createJobPayload) throws IOException
	{
		
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
