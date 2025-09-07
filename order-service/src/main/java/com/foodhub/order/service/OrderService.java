package com.foodhub.order.service;

import com.foodhub.order.dto.*;
import com.foodhub.order.entity.*;
import com.foodhub.order.enums.OrderStatus;
import com.foodhub.order.entity.OrderStatusChange; 
import com.foodhub.order.exception.CustomerNotFoundException;
import com.foodhub.order.exception.OrderNotFoundException;
import com.foodhub.order.client.CustomerServiceClient;
import com.foodhub.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerServiceClient customerServiceClient;

    public OrderResponse createOrder(CreateOrderRequest request) {
        CustomerDto customer = customerServiceClient.getCustomer(request.getCustomerId());
        if (customer == null) throw new CustomerNotFoundException("Customer not found: " + request.getCustomerId());

        Order order = new Order(request.getCustomerId(), request.getRestaurantId(), request.getBranchId());

        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderItemRequest itemReq : request.getItems()) {
            OrderItem item = new OrderItem(itemReq.getItemId(), itemReq.getName(), itemReq.getQuantity(), itemReq.getUnitPrice());
            item.setSpecialInstructions(itemReq.getSpecialInstructions());
            order.getItems().add(item);
            subtotal = subtotal.add(item.getTotalPrice());
        }

        DeliveryAddress deliveryAddress = new DeliveryAddress(
                request.getDeliveryAddress().getStreet(),
                request.getDeliveryAddress().getCity(),
                request.getDeliveryAddress().getPhone()
        );
        deliveryAddress.setPostalCode(request.getDeliveryAddress().getPostalCode());
        order.setDeliveryAddress(deliveryAddress);

        order.setPaymentMethod(request.getPaymentMethod());
        order.setNotes(request.getNotes());

        order.setSubtotal(subtotal);
        order.setDeliveryFee(new BigDecimal("5.00"));
        order.setTax(subtotal.multiply(new BigDecimal("0.15")));
        order.setTotal(order.getSubtotal().add(order.getDeliveryFee()).add(order.getTax()));
        order.setEstimatedDeliveryTime(LocalDateTime.now().plusMinutes(45));

        Order saved = orderRepository.save(order);

        try {
            customerServiceClient.updateLoyaltyPoints(customer.getCustomerId(), saved.getTotal().intValue());
        } catch (Exception e) {
            System.err.println("Failed to update loyalty points: " + e.getMessage());
        }

        return new OrderResponse(saved);
    }

    public OrderResponse getOrder(String orderId) {
        return orderRepository.findById(orderId)
                .map(OrderResponse::new)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + orderId));
    }

    public OrderResponse updateOrderStatus(String orderId, OrderStatus status, String note) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + orderId));
        order.setStatus(status);
        order.getTimeline().add(new OrderStatusChange(status, LocalDateTime.now(), note));
        return new OrderResponse(orderRepository.save(order));
    }

    public List<OrderResponse> getCustomerOrders(String customerId) {
        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    public OrderResponse cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + orderId));
        if (order.getStatus() == OrderStatus.DELIVERED || order.getStatus() == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Cannot cancel order in status: " + order.getStatus());
        }
        order.setStatus(OrderStatus.CANCELLED);
        order.getTimeline().add(new OrderStatusChange(OrderStatus.CANCELLED, LocalDateTime.now(), "Order cancelled by customer"));
        return new OrderResponse(orderRepository.save(order));
    }
}