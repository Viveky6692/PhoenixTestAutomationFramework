package com.demos.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCsvFile_mapTO_POJO {

	public static void main(String[] args) throws IOException, CsvException {
		// TODO Auto-generated method stub
		
		File csvFile = new File("C:\\Eclipse-Workspace\\PhoenixTest_AutomationFramework\\src\\main\\resources\\TestData\\LoginCred.csv");
		FileReader filerd = new FileReader(csvFile);
		CSVReader csvReader = new CSVReader(filerd);  // csv reader constructor 

		
	    CsvToBean<UserPOJO> csvToBean = new CsvToBeanBuilder(csvReader)
	    		.withType(UserPOJO.class)
	    		.withIgnoreEmptyLine(true)
	    		.build();
	    
	   List<UserPOJO> userList =  csvToBean.parse();
	   System.out.println(userList.get(0).getUsername());
	   
	   
	    
	}

}
