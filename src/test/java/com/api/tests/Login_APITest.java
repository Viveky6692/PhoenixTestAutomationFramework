package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Test.pojo.UserCredentials;
import static com.api.utils.Config_Manager.*;

import groovyjarjarantlr4.v4.runtime.atn.SemanticContext.AND;
import io.restassured.http.ContentType;

public class Login_APITest {
	
	
	    @Test
	    public void Login_APITest() throws IOException {
		UserCredentials usercred = new UserCredentials("iamfd","password");
		
		//Config_Manager configManager = new Config_Manager();  
		
		  System.out.println("Running test in "+ System.getProperty("env"));
		    given()
		    .baseUri(getProperty("BASE_URI"))  // method of Config_Manager Class
		    .and()
		    .contentType(ContentType.JSON)
		    .and()
		    .accept(ContentType.JSON)
		    .and()
		    .body(usercred)
		    .log().uri()
		    .log().method()
		    .log().headers()
		    .log().body()
		    
		    .when()
		    .post("login")
		    
		    .then()
		    .statusCode(200)
		    .time(lessThan(3000L))
		    .and()
		    .body("message", equalTo("Success"))
		    .and()
		    .log().body();
		    
		   
	    }
}
