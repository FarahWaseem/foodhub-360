// CustomerControllerIntegrationTest.java
package com.foodhub.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodhub.customer.dto.CustomerCreateRequest;
import com.foodhub.customer.entity.Customer;
import com.foodhub.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@ActiveProfiles("test")
@Testcontainers
@Transactional
class CustomerControllerIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("test_db")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void createCustomer_Success() throws Exception {
        // Given
        CustomerCreateRequest request = new CustomerCreateRequest();
        request.setName("Ahmad Mohammed");
        request.setEmail("ahmad@example.com");
        request.setPhone("+972599123456");
        request.setPassword("password123");

        // When
        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/customers", 
                request, 
                String.class);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(customerRepository.existsByEmail("ahmad@example.com"));
    }

    @Test
    void getCustomer_Success() throws Exception {
        // Given
        Customer customer = new Customer("Ahmad Mohammed", "ahmad@example.com", "+972599123456", "hashedPassword");
        Customer savedCustomer = customerRepository.save(customer);

        // When
        ResponseEntity<String> response = restTemplate.getForEntity(
                "/api/customers/" + savedCustomer.getCustomerId(), 
                String.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Ahmad Mohammed"));
    }
}
