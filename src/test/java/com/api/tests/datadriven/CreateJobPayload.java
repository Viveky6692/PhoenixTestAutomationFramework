package com.api.tests.datadriven;

import java.util.Arrays;
import java.util.List;

import com.Test.pojo.CustomerAddressPOJO;
import com.Test.pojo.CustomerPOJO;
import com.Test.pojo.CustomerProduct_POJO;
import com.Test.pojo.Problems;

public record CreateJobPayload ( 
	
	 int mst_service_location_id,
	 int mst_platform_id,
	 int mst_warrenty_status_id,
	 int mst_oem_id,
	 CustomerPOJO customer,
	 CustomerAddressPOJO customer_address,
	 CustomerProduct_POJO customer_product,
	 List<Problems> problems
)	
	
{
	
}
