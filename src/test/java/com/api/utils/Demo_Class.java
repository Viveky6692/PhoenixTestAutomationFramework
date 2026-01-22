package com.api.utils;

import java.util.Iterator;

import com.dataproviders.api.bean.CreateJobBean;

public class Demo_Class {
	
	public static void main(String[] args)
	{	
		Iterator<CreateJobBean> iterator= CsvreaderUtil.loadCSV("TestData/CreateJobData.csv",CreateJobBean.class);	
		
		while(iterator.hasNext())
		{
			
			System.out.println(iterator.next());
		}
		

    }
}