package com.Test.pojo;

import java.util.Arrays;
import java.util.List;

public record CreateJobAPI_POJO ( 
	
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
