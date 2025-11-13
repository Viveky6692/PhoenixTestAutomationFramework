package com.api.utils;

import java.io.IOException;

import javax.management.relation.Role;

import org.hamcrest.Matchers;

import com.Test.pojo.UserCredentials;
import com.api.constant.Roles;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	
	
	public static RequestSpecification requestSpecification() throws IOException	{
		
		    // take Care of common request Sections (methods)
		    RequestSpecification request =  
		    new RequestSpecBuilder()
		   .setBaseUri(Config_Manager.getProperty("BASE_URI"))
		   .setContentType(ContentType.JSON)
		   .setAccept(ContentType.JSON)
		   .log(LogDetail.URI)
		   .log(LogDetail.METHOD)
		   .log(LogDetail.HEADERS)
		   .log(LogDetail.BODY)
		   .build();
		   
		   return request;
		
	}
	
	 // POST-PUT-PATCH 
	
	public static RequestSpecification requestSpec(Object usercred) throws IOException	{
		
		// method overloading 
		
	    // take Care of common request Sections (methods)
	    RequestSpecification request =  
	    new RequestSpecBuilder()
	   .setBaseUri(Config_Manager.getProperty("BASE_URI"))
	   .setContentType(ContentType.JSON)
	   .setAccept(ContentType.JSON)
	   .setBody(usercred)
	   .log(LogDetail.URI)
	   .log(LogDetail.METHOD)
	   .log(LogDetail.HEADERS)
	   .log(LogDetail.BODY)
	   
	   .build();
	   
	   return request;
	
}
	
	  public static RequestSpecification requestSpecWithAuthToken(Roles role) throws IOException
	  {
		   // to manage response with Auth Token
		  
		 
		  RequestSpecification request =   new RequestSpecBuilder()
				   .setBaseUri(Config_Manager.getProperty("BASE_URI"))	
				   .setContentType(ContentType.JSON)
				   .setAccept(ContentType.JSON)
				   .addHeader("Authorization", AuthTokenProvider.getToken(role))				 
				   .log(LogDetail.URI)
				   .log(LogDetail.METHOD)
				   .log(LogDetail.HEADERS)
				   .log(LogDetail.BODY)
				   .build();
				   
				   return request;
		  
	  }
	  
	  
	  public static RequestSpecification requestSpecWithAuthToken(Roles role,Object payload) throws IOException
	  {
		   // to manage response with Auth Token
		  
		 
		  RequestSpecification request =   new RequestSpecBuilder()
				   .setBaseUri(Config_Manager.getProperty("BASE_URI"))	
				   .setContentType(ContentType.JSON)
				   .setAccept(ContentType.JSON)
				   .addHeader("Authorization", AuthTokenProvider.getToken(role))
				   .setBody(payload)
				   .log(LogDetail.URI)
				   .log(LogDetail.METHOD)
				   .log(LogDetail.HEADERS)
				   .log(LogDetail.BODY)
				   .build();
				   
				   return request;
		  
	  }
	
	  public static ResponseSpecification responseSpecification()
	  {
		  ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		  .expectContentType(ContentType.JSON)
		  .expectStatusCode(200)
		  .expectResponseTime(Matchers.lessThan(2000L))
		  .log(LogDetail.ALL)
		  .build();
		  
		  return responseSpecification;
		  
	  }
	
	  public static ResponseSpecification responseSpecification_Json(int statusCode)
	  {
		  ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		  .expectContentType(ContentType.JSON)
		  .expectStatusCode(200)
		  .expectResponseTime(Matchers.lessThan(2000L))
		  .log(LogDetail.ALL)
		  .build();
		  
		  return responseSpecification;
		  
	  }
	  
	  
	  public static ResponseSpecification responseSpecification_Text(int statusCode)
	  {
		  ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		  .expectStatusCode(statusCode)
		  .expectResponseTime(Matchers.lessThan(2000L))
		  .log(LogDetail.ALL)
		  .build();
		  
		  return responseSpecification;
		  
	  }

}
