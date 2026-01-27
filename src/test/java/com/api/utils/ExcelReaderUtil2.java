package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Test.pojo.UserCredentials;

public class ExcelReaderUtil2 {
	
	private ExcelReaderUtil2()
	{
		
	}
	
	public static Iterator<UserCredentials> loadTestData() throws IOException {
		
	
	InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/Phoenix_testData.xlsx");
	
	//get the sheet 
	XSSFWorkbook myWorkBook = new XSSFWorkbook(is);
	 
	// Focus on Sheet 
	
	XSSFSheet mySheet=  myWorkBook.getSheet("LoginTestData");  // get the worksheet
	
	XSSFRow myRow;
	XSSFCell myCell; 
	
	
	XSSFRow headerRows = mySheet.getRow(0); // start row of the sheet
	
	int usernameIndex =-1;
	int passwordIndex =-1;
	
	for(Cell cell :headerRows)
	{
		if( cell.getStringCellValue().trim().equalsIgnoreCase("username"))
		{
			usernameIndex = cell.getColumnIndex();  // get column number of username
		}
		
		if( cell.getStringCellValue().trim().equalsIgnoreCase("password"))
		{
			passwordIndex = cell.getColumnIndex(); // get column number of password
		}
	}

	 //  System.out.println(usernameIndex + " "+ passwordIndex);
	   
	   
	   int lastRowIndex = mySheet.getLastRowNum();

        XSSFRow rowData;
        UserCredentials userCredentials;
        ArrayList<UserCredentials> userList = new ArrayList<UserCredentials>();
        
        for(int rowIndex =1; rowIndex<=lastRowIndex; rowIndex++)
        {
        	rowData = mySheet.getRow(rowIndex);
        	userCredentials = new UserCredentials( rowData.getCell(usernameIndex).toString() ,rowData.getCell(passwordIndex).toString());
        	userList.add(userCredentials);
        }
        
        System.out.println(userList);
	   
        return userList.iterator();
	}  
}
