package com.foodhub.customer.grpc;

import com.foodhub.customer.dto.CustomerCreateRequest;
import com.foodhub.customer.dto.CustomerResponse;
import com.foodhub.customer.service.CustomerService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {

    @Autowired
    private CustomerService customerService;

    @Override
    public void getCustomer(GetCustomerRequest request, 
                           StreamObserver<com.foodhub.customer.grpc.CustomerResponse> responseObserver) {
        try {
            CustomerResponse customer = customerService.getCustomer(request.getCustomerId());
            
            com.foodhub.customer.grpc.CustomerResponse grpcResponse = 
                com.foodhub.customer.grpc.CustomerResponse.newBuilder()
                    .setCustomerId(customer.getCustomerId())
                    .setName(customer.getName())
                    .setEmail(customer.getEmail())
                    .setPhone(customer.getPhone())
                    .setRegistrationDate(customer.getRegistrationDate().toString())
                    .setLoyaltyPoints(customer.getLoyaltyPoints())
                    .setMembershipLevel(customer.getMembershipLevel().toString())
                    .build();
            
            responseObserver.onNext(grpcResponse);
            responseObserver.onCompleted();
            
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void createCustomer(com.foodhub.customer.grpc.CreateCustomerRequest request, 
                              StreamObserver<com.foodhub.customer.grpc.CustomerResponse> responseObserver) {
        try {
            CustomerCreateRequest createRequest = new CustomerCreateRequest();
            createRequest.setName(request.getName());
            createRequest.setEmail(request.getEmail());
            createRequest.setPhone(request.getPhone());
            createRequest.setPassword(request.getPassword());
            
            CustomerResponse customer = customerService.createCustomer(createRequest);
            
            com.foodhub.customer.grpc.CustomerResponse grpcResponse = 
                com.foodhub.customer.grpc.CustomerResponse.newBuilder()
                    .setCustomerId(customer.getCustomerId())
                    .setName(customer.getName())
                    .setEmail(customer.getEmail())
                    .setPhone(customer.getPhone())
                    .setRegistrationDate(customer.getRegistrationDate().toString())
                    .setLoyaltyPoints(customer.getLoyaltyPoints())
                    .setMembershipLevel(customer.getMembershipLevel().toString())
                    .build();
            
            responseObserver.onNext(grpcResponse);
            responseObserver.onCompleted();
            
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void updateLoyaltyPoints(UpdateLoyaltyPointsRequest request,
                                   StreamObserver<com.foodhub.customer.grpc.CustomerResponse> responseObserver) {
        try {
            CustomerResponse customer = customerService.updateLoyaltyPoints(
                request.getCustomerId(), 
                request.getPoints()
            );
            
            com.foodhub.customer.grpc.CustomerResponse grpcResponse = 
                com.foodhub.customer.grpc.CustomerResponse.newBuilder()
                    .setCustomerId(customer.getCustomerId())
                    .setName(customer.getName())
                    .setEmail(customer.getEmail())
                    .setPhone(customer.getPhone())
                    .setRegistrationDate(customer.getRegistrationDate().toString())
                    .setLoyaltyPoints(customer.getLoyaltyPoints())
                    .setMembershipLevel(customer.getMembershipLevel().toString())
                    .build();
            
            responseObserver.onNext(grpcResponse);
            responseObserver.onCompleted();
            
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
