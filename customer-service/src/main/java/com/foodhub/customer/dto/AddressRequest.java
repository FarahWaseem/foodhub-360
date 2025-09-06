package main.java.com.foodhub.customer.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import main.java.com.foodhub.customer.entity.AddressType;
import main.java.com.foodhub.customer.entity.CustomerAddress;


public class AddressRequest {
    @NotNull(message = "Address type is required")
    private AddressType type;
    
    @NotBlank(message = "Street is required")
    private String street;
    
    @NotBlank(message = "City is required")
    private String city;
    
    private String postalCode;
    
    // Constructors, Getters and Setters
    public AddressRequest() {}
    
    public AddressType getType() {
        return type; 
    }
    public void setType(AddressType type) { 
        this.type = type; 
    }
    
    public String getStreet() { 
        return street; 
    }
    public void setStreet(String street) { 
        this.street = street; 
    }
    
    public String getCity() { 
        return city; 
    }
    public void setCity(String city) { 
        this.city = city; 
    }
    
    public String getPostalCode() { 
        return postalCode; 
    }
    public void setPostalCode(String postalCode) { 
        this.postalCode = postalCode; 
    }
} 
