package com.foodhub.order.graphql;

import com.foodhub.order.dto.CreateOrderRequest;
import com.foodhub.order.dto.CustomerDto;
import com.foodhub.order.dto.OrderResponse;
import com.foodhub.order.enums.OrderStatus;
import com.foodhub.order.grpc.CustomerGrpcClient;
import com.foodhub.order.service.OrderService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderGraphQLResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private OrderService orderService;

    // Query resolvers
    public OrderResponse order(String orderId) {
        return orderService.getOrder(orderId);
    }

    public List<OrderResponse> customerOrders(String customerId) {
        return orderService.getCustomerOrders(customerId);
    }

    // Mutation resolvers
    public OrderResponse createOrder(CreateOrderInput input) {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setCustomerId(input.getCustomerId());
        request.setRestaurantId(input.getRestaurantId());
        request.setBranchId(input.getBranchId());
        request.setNotes(input.getNotes());
        // Map other fields...
        
        return orderService.createOrder(request);
    }

    public OrderResponse updateOrderStatus(String orderId, OrderStatus status, String note) {
        return orderService.updateOrderStatus(orderId, status, note);
    }
}

// Field resolver to get customer data for orders
@Component
public class OrderFieldResolver implements GraphQLResolver<OrderResponse> {

    @Autowired
    private CustomerGrpcClient customerGrpcClient;

    public CustomerDto customer(OrderResponse order) {
        return customerGrpcClient.getCustomer(order.getCustomerId());
    }
}
