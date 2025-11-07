package com.api.utils;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;

import com.Test.pojo.UserCredentials;
import com.api.constant.Roles;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private  AuthTokenProvider()
	{
		// private constructor 
	}
	
	// I want to make request for the login api and want to extract the token and print in console 
	
	    public static String getToken(Roles role) throws IOException   // Roles is enum with user types like qc,fd,sup
  {
	    	
	    UserCredentials userCred = null;   
	 
	    
	    if(role== Roles.FD)   // using the constants from Roles Enum
	    {
	    	  userCred = new UserCredentials("iamfd", "password");
	    }
	    
	    else if(role == Roles.SUP) // Enum named Roles
	    {
	    	  userCred = new UserCredentials("iamsup", "password");
	    }
	    
	    else if(role == Roles.ENG)
	    {
	    	  userCred = new UserCredentials("iameng", "password");
	    }
	    
	    else if(role == Roles.QC)
	    {
	    	  userCred = new UserCredentials("iamqc", "password");
	    }
	    
	    
	    String token =
	    		
	     given()
	    .and()
	    .baseUri(Config_Manager.getProperty("BASE_URI"))
	    .and()
	    .contentType(ContentType.JSON)
	    .and()
	    .body(userCred)
	    
	    .when()
	    .post("login")   // hit the login API to get the tokens
	    
	    .then()
	    .log()
	    .ifValidationFails()
	    .statusCode(200)
	    .body("message", Matchers.equalTo("Success"))   // Matchers class method 
	    .extract()   // extract the bearer token
	    .body()
	    .jsonPath()   // because response is json
	    .getString("data.token");    //token is in String format in double quotes ""
	   
	     
	    // System.out.println("--------->");
	     //System.out.println(token);
	     
	     return token;
	    
  }

}
