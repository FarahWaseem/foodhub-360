package com.foodhub.customer.graphql;
import java.util.*;

public class CreateCustomerInput {
    private String name;
    private String email;
    private String phone;
    private String password;
    private List<AddressInput> addresses;
    
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public List<AddressInput> getAddresses() { return addresses; }
    public void setAddresses(List<AddressInput> addresses) { this.addresses = addresses; }
}

