package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertNotEqualsDeep;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.Config_Manager_OLD.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetails_APITest {
	
	   @Test
	   public void UserDetails_APITest() throws IOException
	   
	   {
		  
		   System.out.println("-----> "+ System.getProperty("env"));   // print Env used
		    Header authheader = new Header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3NjE2Mjc2NTR9.GtqHqNTmSJ20ccY0yfccDNigBl1U_42e9uNb2W2BYao");
		    
		    given()
		   .baseUri(getProperty("BASE_URI"))
		   .and()
		   .header(authheader)
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
