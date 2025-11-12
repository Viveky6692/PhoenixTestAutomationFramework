package com.api.tests;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.Config_Manager;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	
	
	@Test
	public void verifyCountAPI_Response()
	{
		
		try {
			
			given()
		    .spec(SpecUtil.requestSpecWithAuthToken(Roles.FD))
			.when()
			.get("/dashboard/count")
			 
			 .then()
			 .spec(SpecUtil.responseSpecification())   
			 .body("message", Matchers.equalTo("Success"))       // Verify success message 
			 .time(Matchers.lessThan(2000L))		// verify response time is less than 20 Mliseconds 
			 .body("data", Matchers.notNullValue())
			 .body("data.size()", Matchers.equalTo(3))
			 .body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
			 .body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
			 .body("data.key", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
			 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath	("JsonSchema_Folder/CountAPI_Schema.json"))
			 .body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));
		     
			 
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		@Test
		public void CountAPI_MissingAuthToken() throws IOException
		{
			
			 given()
			 .spec(SpecUtil.requestSpecification()) // Config manager class to provide Base URI
			
			
			.when()
			.get("/dashboard/count")

			 .then()
			 .spec(SpecUtil.responseSpecification_Text(401));
					
		}
				
	}
	

