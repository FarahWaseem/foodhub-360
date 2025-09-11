package com.foodhub.order.grpc;

import com.foodhub.customer.grpc.CustomerServiceGrpc;
import com.foodhub.customer.grpc.GetCustomerRequest;
import com.foodhub.customer.grpc.UpdateLoyaltyPointsRequest;
import com.foodhub.order.dto.CustomerDto;
import io.grpc.ManagedChannel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class CustomerGrpcClient {

    @GrpcClient("customer-service")
    private CustomerServiceGrpc.CustomerServiceBlockingStub customerServiceStub;

    public CustomerDto getCustomer(String customerId) {
        GetCustomerRequest request = GetCustomerRequest.newBuilder()
            .setCustomerId(customerId)
            .build();
        
        com.foodhub.customer.grpc.CustomerResponse response = customerServiceStub.getCustomer(request);
        
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(response.getCustomerId());
        customerDto.setName(response.getName());
        customerDto.setEmail(response.getEmail());
        customerDto.setPhone(response.getPhone());
        customerDto.setLoyaltyPoints(response.getLoyaltyPoints());
        customerDto.setMembershipLevel(response.getMembershipLevel());
        
        return customerDto;
    }

    public CustomerDto updateLoyaltyPoints(String customerId, Integer points) {
        UpdateLoyaltyPointsRequest request = UpdateLoyaltyPointsRequest.newBuilder()
            .setCustomerId(customerId)
            .setPoints(points)
            .build();
        
        com.foodhub.customer.grpc.CustomerResponse response = customerServiceStub.updateLoyaltyPoints(request);
        
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(response.getCustomerId());
        customerDto.setName(response.getName());
        customerDto.setEmail(response.getEmail());
        customerDto.setPhone(response.getPhone());
        customerDto.setLoyaltyPoints(response.getLoyaltyPoints());
        customerDto.setMembershipLevel(response.getMembershipLevel());
        
        return customerDto;
    }
}