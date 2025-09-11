package com.foodhub.test;

import com.foodhub.customer.grpc.CustomerServiceGrpc;
import com.foodhub.customer.grpc.GetCustomerRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcTestClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 9090)
            .usePlaintext()
            .build();
        
        CustomerServiceGrpc.CustomerServiceBlockingStub stub = 
            CustomerServiceGrpc.newBlockingStub(channel);
        
        GetCustomerRequest request = GetCustomerRequest.newBuilder()
            .setCustomerId("customer-123")
            .build();
        
        try {
            var response = stub.getCustomer(request);
            System.out.println("Customer: " + response.getName());
            System.out.println("Email: " + response.getEmail());
            System.out.println("Loyalty Points: " + response.getLoyaltyPoints());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.shutdown();
        }
    }
}
