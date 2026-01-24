package com.Test.pojo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateJobPayload(

        int mst_service_location_id,
        int mst_platform_id,
        int mst_warrenty_status_id,
        int mst_oem_id,

        Customer customer,

        @JsonProperty("customer_address")
        CustomerAddress customer_address,

        CustomerProduct customer_product,

        List<Problems> problems
) {
}
