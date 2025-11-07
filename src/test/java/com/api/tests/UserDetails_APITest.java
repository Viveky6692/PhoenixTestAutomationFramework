package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertNotEqualsDeep;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Roles;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.Config_Manager_OLD.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import com.api.constant.Roles;

public class UserDetails_APITest {
	
	   @Test
	   public void UserDetails_APITest() throws IOException
	   
	   {
		  
		   System.out.println("Running test in "+ System.getProperty("env"));   // print Env used
		    Header authheader = new Header("Authorization",getToken(Roles.FD));  // method of AuthTokenProvider class
		    // returns the dynamically generated tokens
		    
		    given()
		   .baseUri(getProperty("BASE_URI"))
		   .and()
		   .header(authheader) // provide the authenticaton token
		   .contentType(ContentType.JSON)
		   .and()
		   .accept(ContentType.JSON)
		   .log().uri()
		   .log().method()
		   .log().body()
		   .log().headers()
		   
		   .when()
		   .get("userdetails")
		   
		   .then()
		   .statusCode(200)
		   .and()
		   .time(lessThan(4000L))
		   .body("message",equalTo("Success"))
		   .and()
		   .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema_Folder/UserDetails_Schema.json"))
		   .and()
		   .log().all();
		    	   
	   }
       
	   
             
}
