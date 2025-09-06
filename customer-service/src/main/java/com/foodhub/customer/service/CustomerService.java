package com.foodhub.customer.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import main.java.com.foodhub.customer.repository.CustomerRepository;
import main.java.com.foodhub.customer.entity.Customer;
import main.java.com.foodhub.customer.dto.CustomerCreateRequest;
import main.java.com.foodhub.customer.dto.CustomerResponse;
import main.java.com.foodhub.customer.exception.CustomerNotFoundException;
import main.java.com.foodhub.customer.exception.EmailAlreadyExistsException;
import java.util.List;
import java.util.stream.Collectors; 
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import main.java.com.foodhub.customer.entity.MembershipLevel;
import main.java.com.foodhub.customer.dto.AddressRequest;
import main.java.com.foodhub.customer.entity.CustomerAddress;

@Service
@Transactional
public class CustomerService  {
    
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public CustomerResponse createCustomer(CustomerCreateRequest dto) {
    if (customerRepository.existsByEmail(dto.getEmail())) {
        throw new EmailAlreadyExistsException("Email already exists: " + dto.getEmail());
    }
    Customer c = new Customer(
        dto.getName(),
        dto.getEmail(),
        dto.getPhone(),
        passwordEncoder.encode(dto.getPassword())
    );
    dto.getAddresses().forEach(a -> c.getAddresses().add(
        new CustomerAddress(c, a.getType(), a.getStreet(), a.getCity())
    ));
    return new CustomerResponse(customerRepository.save(c));
}

public CustomerResponse getCustomer(String id) {
    return customerRepository.findById(id)
        .map(CustomerResponse::new)
        .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id));
}

public CustomerResponse updateLoyaltyPoints(String id, Integer points) {
    Customer c = customerRepository.findById(id)
        .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id));
    c.setLoyaltyPoints(c.getLoyaltyPoints() + points);
    return new CustomerResponse(customerRepository.save(c));
}

public List<CustomerResponse> getAllCustomers() {
    return customerRepository.findAll()
        .stream()
        .map(CustomerResponse::new)
        .collect(Collectors.toList());
}
}
