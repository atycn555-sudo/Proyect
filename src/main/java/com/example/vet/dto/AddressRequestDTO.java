package com.example.vet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddressRequestDTO {

    @NotBlank(message = "The street cannot be empty.")
    @Size(max = 100, message = "The street name cannot have more than 100 characters.")
    private String street;

    @Size(max = 20, message = "The external number cannot be longer than 20 characters")
    private String externalNumber;

    @Size(max = 100, message = "The colony cannot have more than 100 characters")
    private String neighborhood;

    @NotBlank(message = "The city cannot be empty.")
    @Size(max = 100, message = "The city name cannot exceed 100 characters.")
    private String city;

    @Size(max = 10, message = "The postal code cannot be longer than 10 characters")
    private String zipCode;

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getExternalNumber() { return externalNumber; }
    public void setExternalNumber(String externalNumber) { this.externalNumber = externalNumber; }
    public String getNeighborhood() { return neighborhood; }
    public void setNeighborhood(String neighborhood) { this.neighborhood = neighborhood; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
}
