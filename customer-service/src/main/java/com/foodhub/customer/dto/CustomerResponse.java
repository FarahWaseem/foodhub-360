package main.java.com.foodhub.customer.dto;
import main.java.com.foodhub.customer.entity.Customer;
import main.java.com.foodhub.customer.entity.MembershipLevel;
import java.time.LocalDateTime;
import java.util.* ;
import java.util.stream.Collectors;
import main.java.com.foodhub.customer.dto.AddressResponse;
import java.time.format.DateTimeFormatter;
import java.time.ZoneOffset;
import java.time.* ;
import java.util.stream.* ;
import java.util.List;
import java.util.ArrayList;

public class CustomerResponse  {
    private String customerId;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime registrationDate;
    private Integer loyaltyPoints;
    private MembershipLevel membershipLevel;
    private List<AddressResponse> addresses;
    
    // Constructors, Getters and Setters
    public CustomerResponse() {}
    
    public CustomerResponse(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.registrationDate = customer.getRegistrationDate();
        this.loyaltyPoints = customer.getLoyaltyPoints();
        this.membershipLevel = customer.getMembershipLevel();
        this.addresses = customer.getAddresses().stream()
            .map(AddressResponse::new)
            .collect(Collectors.toList());
    }
    
    // All getters and setters...
    public String getCustomerId() { 
        return customerId; 
    }
    public void setCustomerId(String customerId) { 
        this.customerId = customerId; 
    }
    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }
    public String getPhone() { 
        return phone; 
    }
    public void setPhone(String phone) { 
        this.phone = phone; 
    }
    public LocalDateTime getRegistrationDate() { 
        return registrationDate; 
    }
    public void setRegistrationDate(LocalDateTime registrationDate) { 
        this.registrationDate = registrationDate; 
    }
    public Integer getLoyaltyPoints() { 
        return loyaltyPoints; 
    }
    public void setLoyaltyPoints(Integer loyaltyPoints) { 
        this.loyaltyPoints = loyaltyPoints; 
    }
    public MembershipLevel getMembershipLevel() { 
        return membershipLevel; 
    }
    public void setMembershipLevel(MembershipLevel membershipLevel) { 
        this.membershipLevel = membershipLevel; 
    }
    public List<AddressResponse> getAddresses() { 
        return addresses; 
    }
    public void setAddresses(List<AddressResponse> addresses) { 
        this.addresses = addresses; 
    }
}

