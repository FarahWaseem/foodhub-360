package main.java.com.foodhub.customer.service;
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
}
