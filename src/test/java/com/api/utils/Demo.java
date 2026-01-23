package com.api.utils;

import java.util.Iterator;

import com.dataproviders.api.bean.CreateJobBean;

public class Demo {
	
	public static void main (String [] args)
	{
		Iterator<CreateJobBean>  iterator     = CsvreaderUtil.loadCSV("TestData/CreateJobData.csv", CreateJobBean.class);
	//	CsvreaderUtil.loadCSV(null, null)
		
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}

}
