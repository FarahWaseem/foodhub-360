package main.java.com.foodhub.customer.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.* ;

import main.java.com.foodhub.customer.service.CustomerService;
import main.java.com.foodhub.customer.dto.CustomerCreateRequest;
import main.java.com.foodhub.customer.dto.CustomerResponse;
import main.java.com.foodhub.customer.dto.AddressRequest;
import main.java.com.foodhub.customer.entity.AddressType;
import main.java.com.foodhub.customer.entity.MembershipLevel;
import main.java.com.foodhub.customer.entity.CustomerAddress;
import main.java.com.foodhub.customer.entity.Customer;


@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    
    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerCreateRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String customerId) {
        CustomerResponse response = customerService.getCustomer(customerId);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{customerId}/loyalty")
    public ResponseEntity<CustomerResponse> updateLoyaltyPoints(
            @PathVariable String customerId, 
            @RequestParam Integer points) {
        CustomerResponse response = customerService.updateLoyaltyPoints(customerId, points);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
}
