package com.foodhub.customer.service;
import com.foodhub.customer.dto.CustomerCreateRequest;
import com.foodhub.customer.dto.CustomerResponse;
import com.foodhub.customer.entity.Customer;
import com.foodhub.customer.exception.EmailAlreadyExistsException;
import com.foodhub.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomerService customerService;

    private CustomerCreateRequest createRequest;
    private Customer customer;

    @BeforeEach
    void setUp() {
        createRequest = new CustomerCreateRequest();
        createRequest.setName("Ahmad Mohammed");
        createRequest.setEmail("ahmad@example.com");
        createRequest.setPhone("+972599123456");
        createRequest.setPassword("password123");
        
        customer = new Customer("Ahmad Mohammed", "ahmad@example.com", "+972599123456", "hashedPassword");
        customer.setCustomerId("cust-123");
    }

    @Test
    void createCustomer_Success() {
        // Given
        when(customerRepository.existsByEmail(createRequest.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(createRequest.getPassword())).thenReturn("hashedPassword");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // When
        CustomerResponse response = customerService.createCustomer(createRequest);

        // Then
        assertNotNull(response);
        assertEquals(customer.getName(), response.getName());
        assertEquals(customer.getEmail(), response.getEmail());
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void createCustomer_EmailAlreadyExists() {
        // Given
        when(customerRepository.existsByEmail(createRequest.getEmail())).thenReturn(true);

        // When & Then
        assertThrows(EmailAlreadyExistsException.class, () -> {
            customerService.createCustomer(createRequest);
        });
        
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void getCustomer_Success() {
        // Given
        when(customerRepository.findById("cust-123")).thenReturn(Optional.of(customer));

        // When
        CustomerResponse response = customerService.getCustomer("cust-123");

        // Then
        assertNotNull(response);
        assertEquals(customer.getName(), response.getName());
        assertEquals(customer.getEmail(), response.getEmail());
    }

    @Test
    void updateLoyaltyPoints_Success() {
        // Given
        when(customerRepository.findById("cust-123")).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // When
        CustomerResponse response = customerService.updateLoyaltyPoints("cust-123", 50);

        // Then
        assertNotNull(response);
        assertEquals(50, customer.getLoyaltyPoints());
        verify(customerRepository).save(customer);
    }
}