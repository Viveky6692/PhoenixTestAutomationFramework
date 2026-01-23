	package com.api.tests;

import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.Test.pojo.CreateJobAPI_POJO;
import com.Test.pojo.CreateJobPayload;
import com.Test.pojo.Customer;
import com.Test.pojo.CustomerAddress;
import com.Test.pojo.CustomerAddressPOJO;
import com.Test.pojo.CustomerPOJO;
import com.Test.pojo.CustomerProduct;
import com.Test.pojo.CustomerProduct_POJO;
import com.Test.pojo.Problems;
import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.Config_Manager;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;
import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CreateJobAPITest2 {
	
	
	
	@Test  
	public void createJobAPITest() throws IOException
	{
		
		final String COUNTRY = "India";
		
		Faker faker = new Faker(new Locale("en-IND")); //Help me to Create India specific fake Data
		
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String MobileNumber = faker.numerify("98########");
		String alternateMobileNumber = faker.numerify("7########");
		String emaild = faker.internet().emailAddress();
		//String alternateemaild = faker.internet().emailAddress();
		
		Customer customer = new Customer(fname, lname, MobileNumber, alternateMobileNumber, emaild, emaild);
		
		
		//CustomerAddress Fake object Creation
		 String flat_number = faker.numerify("###");
		 String apartment_name = faker.address().streetName();
		 String street_name =  faker.address().streetName();
		 String landmark=  faker.address().streetName();
		 String area = faker.address().streetName();
		 String pincode = faker.numerify("######");
		 String state = faker.address().state();
		 
		 
		
		CustomerAddress customerAddress = new CustomerAddress(flat_number, apartment_name, street_name, landmark, area, pincode, COUNTRY, state);
		//System.out.println(customerAddress);
		
		
		// Customer Product Fake object Creation
		
		 String dop= DateTimeUtil.getTimeWithDaysAgo(10);
		 String serial_number= faker.numerify("###############");	
		 String popurl= faker.internet().url();
		 

		CustomerProduct customerProduct = new CustomerProduct(dop, serial_number, serial_number, serial_number, popurl, 1, 1);
		
		
		String fakeRemark = faker.lorem().sentence(10);
		Random random = new Random();
		int problemId = random.nextInt(26)+1;
		
		Problems problems = new Problems(problemId,fakeRemark);
		
		
		
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload payload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
		
		 given()
		.spec(SpecUtil.requestSpecWithAuthToken(Roles.FD, payload))
		
		.when()
		.post("job/create")
		
		.then()
		.log().all()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema_Folder/CreateJobAPIResponse.json"))
		.body("message",Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number",Matchers.startsWith("JOB_"));  
	}

}
