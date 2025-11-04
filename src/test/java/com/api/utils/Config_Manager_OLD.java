package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.Flow.Publisher;
import java.io.FileReader;
import java.io.IOException;


public class Config_Manager_OLD {
	
	 private static Properties prop =new Properties();  // made the static variable 
	 
	 private Config_Manager_OLD()
	 {
		 // private constructor of the class
	 }
	 
	 static {
		 
	// Operations of loading the File in the Memory 
		// Static Block - It will be executed only once during Class Loading Time.
	 
	 File configFile = new File(System.getProperty("user.dir") + File.separator+"src"+File.separator+"test"+
	 File.separator+"resources" +File.separator+"Config"+File.separator +"config.properties");
	 
	 FileReader filereader=null;
	try {
		filereader = new FileReader(configFile);
		prop.load(filereader);
	} 
	
	  catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
	 
	 
}  // end of static block
	 
	 
	 public static String getProperty (String key) throws IOException
	 {

      	 return prop.getProperty(key);
	 
	 }	 


}
