package com.foodhub.customer.graphql;

import com.foodhub.customer.dto.CustomerCreateRequest;
import com.foodhub.customer.dto.CustomerResponse;
import com.foodhub.customer.service.CustomerService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerGraphQLResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CustomerService customerService;

    // Query resolvers
    public CustomerResponse customer(String customerId) {
        return customerService.getCustomer(customerId);
    }

    public List<CustomerResponse> customers() {
        return customerService.getAllCustomers();
    }

    // Mutation resolvers
    public CustomerResponse createCustomer(CreateCustomerInput input) {
        CustomerCreateRequest request = new CustomerCreateRequest();
        request.setName(input.getName());
        request.setEmail(input.getEmail());
        request.setPhone(input.getPhone());
        request.setPassword(input.getPassword());
        
        return customerService.createCustomer(request);
    }
}
