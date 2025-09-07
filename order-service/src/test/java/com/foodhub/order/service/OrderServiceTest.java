package com.foodhub.order.service;

import com.foodhub.order.client.CustomerServiceClient;
import com.foodhub.order.dto.CreateOrderRequest;
import com.foodhub.order.dto.CustomerDto;
import com.foodhub.order.dto.OrderResponse;
import com.foodhub.order.entity.Order;
import com.foodhub.order.enums.OrderStatus;
import com.foodhub.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerServiceClient customerServiceClient;

    @InjectMocks
    private OrderService orderService;

    private CreateOrderRequest createRequest;
    private CustomerDto customerDto;
    private Order order;

    @BeforeEach
    void setUp() {
        customerDto = new CustomerDto();
        customerDto.setCustomerId("cust-123");
        customerDto.setName("Ahmad Mohammed");
        customerDto.setEmail("ahmad@example.com");

        order = new Order("cust-123", "rest-001", "branch-001");
        order.setOrderId("order-123");
    }

    @Test
    void createOrder_Success() {
        // Given
        when(customerServiceClient.getCustomer("cust-123")).thenReturn(customerDto);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // When
        // Note: You'll need to set up createRequest with proper data
        // OrderResponse response = orderService.createOrder(createRequest);

        // Then
        verify(customerServiceClient).getCustomer("cust-123");
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void getOrder_Success() {
        // Given
        when(orderRepository.findById("order-123")).thenReturn(Optional.of(order));

        // When
        OrderResponse response = orderService.getOrder("order-123");

        // Then
        assertNotNull(response);
        assertEquals(order.getOrderId(), response.getOrderId());
    }

    @Test
    void updateOrderStatus_Success() {
        // Given
        when(orderRepository.findById("order-123")).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // When
        OrderResponse response = orderService.updateOrderStatus("order-123", OrderStatus.CONFIRMED, "Order confirmed");

        // Then
        assertNotNull(response);
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
        verify(orderRepository).save(order);
    }
}