package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

import com.Test.pojo.UserCredentials;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {

	
	public static <T> Iterator<T> loadJson(String fileName, Class<T[]> clazz)  {
		// TODO Auto-generated method stub
		
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			
		    ObjectMapper objectMapper = new ObjectMapper();
		    
		 // maps is which is demo.json file with UserCred POJO class
		    T[] classArray;
		    List<T> list=null;
		    
			try {
				classArray =  objectMapper.readValue(is, clazz);
				list = Arrays.asList(classArray);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		   
		  
		    // converted UserCredentials Array to List for using iterator 
		 
	         return list.iterator();
		   
			
	}

}
