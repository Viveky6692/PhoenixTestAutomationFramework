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
import com.api.request.model.CreateJobPayload;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtil3 {
	
	public static void main(String args[]) throws IOException {
		// TODO Auto-generated method stub
         
		//ExcelReaderUtil2.loadTestData("TestData/Phoenix_testData.xlsx", "CreateJobTestData", CreateJobBeanMapper.class);
		Iterator<CreateJobBean> iterator = ExcelReaderUtil2.loadTestData("TestData/Phoenix_testData.xlsx", "CreateJobTestData", CreateJobBean.class);
		while(iterator.hasNext())
		{
			CreateJobBean bean = iterator.next();
			com.Test.pojo.CreateJobPayload  createJobPayload  = CreateJobBeanMapper.mapper(bean) ;  // convert data object from bean to bean mapper
		    System.out.println(createJobPayload);   
		}
		
	}  
}
