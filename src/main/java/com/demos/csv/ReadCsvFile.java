package com.demos.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCsvFile {

	public static void main(String[] args) throws IOException, CsvException {
		// TODO Auto-generated method stub
		
		File csvFile = new File("C:\\Eclipse-Workspace\\PhoenixTest_AutomationFramework\\src\\main\\resources\\TestData\\LoginCred.csv");
		FileReader filerd = new FileReader(csvFile);
		CSVReader csvReader = new CSVReader(filerd);  // csv reader constructor 

	List<String[]> dataList = csvReader.readAll();
		
		for(String[] dataArray : dataList)
		{
			for(String data: dataArray)
			{
				System.out.println(data+" ");
			}
			
			System.out.println("");
		}
		
		
	}
}
