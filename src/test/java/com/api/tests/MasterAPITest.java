package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.Config_Manager;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class MasterAPITest {
	
	
	@Test
	public void masterAPITest() throws IOException
	{
		
		 given()
		.baseUri(Config_Manager.getProperty("BASE_URI"))
		.and()
		.header("Authorization",AuthTokenProvider.getToken(Roles.FD))
		.and()
        .contentType("")    // explicitly keep content type as blank
		//.log().all()
		
         .when()
         .post("master")   // default Content-type application/url/formencoded
         
         .then()
         .statusCode(200)  
         .time(Matchers.lessThan(2000L))
         .body("message", Matchers.equalTo("Success"))
         .body("data",Matchers.notNullValue())
         .body("data",Matchers.hasKey("mst_oem"))
         .body("data", Matchers.hasKey("mst_model"))
         .body("data", Matchers.hasKey("mst_action_status"))
         .body("$", Matchers.hasKey("message"))
         .body("data.mst_oem.size()", Matchers.equalTo(2))
         .body("data.mst_model.size()",Matchers.equalTo(3))
         .body("data.mst_oem.id",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
         .body("data.mst_oem.name",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
         .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema_Folder/MasterAPI_Schema.json"))
        // .log().body()
         .log().status();
		 
				 	
	}
	
	
	   @Test
	   
	   public void invalidTokenMasterAPI() throws IOException
	   {

			 given()
			.baseUri(Config_Manager.getProperty("BASE_URI"))
			.and()
			 
			.and()
	        .contentType("")    // explicitly keep content type as blank
			//.log().all()
			
	         .when()
	         .post("master")   // default Content-type application/url/formencoded
	         
	         .then()
	         .statusCode(401);
		   
		   
	   }

}
