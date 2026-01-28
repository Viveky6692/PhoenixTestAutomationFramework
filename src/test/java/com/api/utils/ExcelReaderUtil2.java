package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Test.pojo.UserCredentials;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtil2 {
	
	private ExcelReaderUtil2()
	{
		
	}
	
	public static <T> Iterator<T> loadTestData(String xlsxFile, String sheetName, Class<T> clazz) throws IOException {
		
	
	InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(xlsxFile);
	
	//get the sheet 
	XSSFWorkbook myWorkBook = new XSSFWorkbook(is);
	 
	// Focus on Sheet 
	
	XSSFSheet mySheet=  myWorkBook.getSheet(sheetName);  // get the worksheet
	    
	  List<T> dataList    = Poiji.fromExcel(mySheet,clazz);
	  return dataList.iterator();
	}  
}
