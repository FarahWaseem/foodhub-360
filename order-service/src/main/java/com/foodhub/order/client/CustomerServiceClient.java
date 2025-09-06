package com.foodhub.order.client;

import com.foodhub.order.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "customer-service", url = "${customer.service.url:http://localhost:8081}")
public interface CustomerServiceClient {

    @GetMapping("/api/customers/{customerId}")
    CustomerDto getCustomer(@PathVariable("customerId") String customerId);

    @PutMapping("/api/customers/{customerId}/loyalty")
    CustomerDto updateLoyaltyPoints(@PathVariable("customerId") String customerId,
                                    @RequestParam("points") Integer points);
}