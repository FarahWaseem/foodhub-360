package main.java.com.foodhub.customer.dto;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;



public class CustomerCreateRequest{
@NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Phone is required")
    private String phone;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    
    private List<AddressRequest> addresses = new ArrayList<>();
    
    // Constructors, Getters and Setters
    public CustomerCreateRequest() {}
    
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
    
    public String getPassword() { 
        return password; 
    }
    public void setPassword(String password) { 
        this.password = password; 
    }
    
    public List<AddressRequest> getAddresses() { 
        return addresses; 
    }
    public void setAddresses(List<AddressRequest> addresses) { 
        this.addresses = addresses; 
    }
}



