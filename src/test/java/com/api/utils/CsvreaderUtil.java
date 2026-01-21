package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class CsvreaderUtil {


		// TODO Auto-generated method stub

	   private CsvreaderUtil()
	   {
		   
	   }
		
		public static void loadCSV (String pathOfCSVFile) {
			// TODO Auto-generated method stub
			
		/*	File csvFile = new File("C:\\Eclipse-Workspace\\PhoenixTest_AutomationFramework\\src\\main\\resources\\TestData\\LoginCred.csv");
			FileReader filerd = new FileReader(csvFile);
			CSVReader csvReader = new CSVReader(filerd);  // csv reader constructor 
		*/	
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/LoginCred.csv");
			InputStreamReader isr = new InputStreamReader(is);
			CSVReader csvReader = new CSVReader(isr);

			
		    CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
		    		.withType(UserBean.class)
		    		.withIgnoreEmptyLine(true)
		    		.build();
		    
		   List<UserBean> userList =  csvToBean.parse();
		   System.out.println(userList.get(0).getUsername());
		   
		   
	}

}
