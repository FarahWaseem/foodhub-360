package com.foodhub.customer.service;

import com.foodhub.customer.dto.*;
import com.foodhub.customer.entity.*;
import com.foodhub.customer.enums.MembershipLevel;
import com.foodhub.customer.exception.*;
import com.foodhub.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerResponse createCustomer(CustomerCreateRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }

        Customer customer = new Customer(
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                passwordEncoder.encode(request.getPassword())
        );

        for (AddressRequest addr : request.getAddresses()) {
            CustomerAddress address = new CustomerAddress(customer, addr.getType(), addr.getStreet(), addr.getCity());
            address.setPostalCode(addr.getPostalCode());
            customer.getAddresses().add(address);
        }

        Customer saved = customerRepository.save(customer);
        return new CustomerResponse(saved);
    }
    public CustomerResponse getCustomer(String customerId) {
    return customerRepository.findById(customerId)
            .map(CustomerResponse::new)
            .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + customerId));
}

public CustomerResponse updateLoyaltyPoints(String customerId, Integer points) {
    Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + customerId));
    customer.setLoyaltyPoints(customer.getLoyaltyPoints() + points);
    updateMembershipLevel(customer);
    return new CustomerResponse(customerRepository.save(customer));
}

private void updateMembershipLevel(Customer customer) {
    int points = customer.getLoyaltyPoints();
    if (points >= 1000) customer.setMembershipLevel(MembershipLevel.PLATINUM);
    else if (points >= 500) customer.setMembershipLevel(MembershipLevel.GOLD);
    else if (points >= 100) customer.setMembershipLevel(MembershipLevel.SILVER);
    else customer.setMembershipLevel(MembershipLevel.BRONZE);
}

public List<CustomerResponse> getAllCustomers() {
    return customerRepository.findAll().stream().map(CustomerResponse::new).collect(Collectors.toList());
}
}