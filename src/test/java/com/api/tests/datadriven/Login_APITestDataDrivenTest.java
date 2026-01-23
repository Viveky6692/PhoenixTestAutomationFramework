package com.api.tests.datadriven;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Test.pojo.UserCredentials;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import static com.api.utils.Config_Manager.*;

import groovyjarjarantlr4.v4.runtime.atn.SemanticContext.AND;
import io.restassured.http.ContentType;

public class Login_APITestDataDrivenTest {
	
	
	    @Test(description = " Verifying if Login API is working for FD User",
	    		groups = {"api","regression","datadriven"},
	    		dataProviderClass = com.dataproviders.DataProviderUtils.class,
	    		dataProvider = "LoginAPIDataProvider"
	    		
	    		)
	    		

	    public void Login_APITest(UserBean userbean) throws IOException {

		   
		  given()
		    .spec(SpecUtil.requestSpec(userbean))
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
