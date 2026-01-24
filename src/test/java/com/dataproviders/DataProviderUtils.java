package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.Test.pojo.CreateJobPayload;
import com.Test.pojo.UserCredentials;

import org.testng.annotations.DataProvider;

import com.api.utils.CreateJobBeanMapper;
import com.api.utils.CsvreaderUtil;
import com.api.utils.JsonReaderUtil;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider (name= "LoginAPIDataProvider",parallel = true)
  	public static Iterator<UserBean> LoginAPIDataProvider() {
		
  		return CsvreaderUtil.loadCSV("TestData/LoginCred.csv", UserBean.class);
  		
  		
  		
  	}
	
	@DataProvider (name= "CreateJobAPIDataProvider",parallel = true)
  	public static Iterator<CreateJobPayload> CreateJobAPIDataProvider() {
		
  		Iterator<CreateJobBean> createJobBeanIterator =  CsvreaderUtil.loadCSV("TestData/CreateJobData.csv", CreateJobBean.class);
  		
  		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
  		
  		CreateJobBean tempBean;
  		CreateJobPayload tempPayload;
  		
  		while(createJobBeanIterator.hasNext())
  		{
  			tempBean = createJobBeanIterator.next();
  			tempPayload = CreateJobBeanMapper.mapper(tempBean);
  			payloadList.add(tempPayload);
  			
  		}
  		
  		return payloadList.iterator();
  		
  	}
	
    
	@DataProvider (name= "LoginAPIJsonDataProvider",parallel = true)
  	public static Iterator<UserCredentils> LoginAPIJsonDataProvider() {
		
  		return JsonReaderUtil.loadJson("TestData/LoginAPITestData.json", UserCredentials[].class); 
  		
	}
	
	
	@DataProvider (name= "CreateJobAPIJsonDataProvider",parallel = true)
  	public static Iterator<CreateJobPayload> CreateJobAPIJsonDataProvider() {
		
  		return JsonReaderUtil.loadJson("TestData/CreateJobAPIData.json", CreateJobPayload[].class); 
  		
	}
 	
}
