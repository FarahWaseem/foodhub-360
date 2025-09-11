package com.foodhub.customer.graphql;

public class AddressInput {
    private String type;
    private String street;
    private String city;
    private String postalCode;
    
    // Getters and setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
}
