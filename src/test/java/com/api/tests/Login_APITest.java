package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Test.pojo.UserCredentials;
import com.api.utils.SpecUtil;

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
		    .spec(SpecUtil.requestSpec(usercred))
		    //.body(usercred)
		    
		    
		    .when()
		    .post("login")
		    
		    .then()
		    .spec(SpecUtil.responseSpecification())
		    .and()
		    .body("message", equalTo("Success"))
		    .and()
		    .log().body();
		    
		   
	    }
}
