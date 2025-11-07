package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.Flow.Publisher;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;


public class Config_Manager {
	
	
	 private static String path = "Config/config.properties";
	 private static String env;   // env variable 
	 private static Properties prop =new Properties();  // made the static variable 

	
	 
	 
	 private Config_Manager()
	 {
		 // private constructor of the class
	 }
	 
	 static {
		 
		//env= System.getenv("env"); 
		env = System.getProperty("env", "test");   // if we do mvn test only then env is null so used this for default env value to test
		env = env.toLowerCase().trim();   // convert the env input to lowercase as java is case sensitive
		
		switch (env)
		{
		
		  case "dev" -> path = "Config/config.Dev.properties";  
		
		  
		  case "test" -> path = "Config/config.Test.properties"; 
			
		  		  
		  case "uat" -> path = "Config/config.UAT.properties";  
		
		  		  
		  default ->path = "Config/config.properties";  
			
				  
		}
		 
		
		 

		try (InputStream input = Thread.currentThread()   // Returns File path as input Stream independent of Operating Systems
		        .getContextClassLoader()    // Load resource File from src/test/resources folder 
		        .getResourceAsStream(path)) {

		    if (input == null) {
		        throw new RuntimeException("Can't find the file at path: " + path);
		    }

		    prop.load(input);  // ✅ Directly load from InputStream

		} catch (IOException e) {
		    e.printStackTrace();
		}
	 
}  // end of static block
	 
	 
	 public static String getProperty (String key) throws IOException
	 {

      	 return prop.getProperty(key);
	 
	 }	 


}
