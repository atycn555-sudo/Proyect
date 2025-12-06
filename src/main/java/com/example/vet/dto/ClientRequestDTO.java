package com.example.vet.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClientRequestDTO {

    @NotBlank(message = "The name cannot be empty")
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters long")
    private String firstName;

    @Size(max = 100, message = "The lastName cannot be longer than 100 characters")
    private String lastName;

    @Size(max = 20, message = "The phone number cannot have more than 20 characters.")
    private String phone;

    @NotBlank(message = "The email cannot be empty.")
    @Email(message = "The email format is invalid.")
    private String email;

    @NotNull(message = "The address cannot be null")
    @Valid 
    private AddressRequestDTO address;
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public AddressRequestDTO getAddress() { return address; }
    public void setAddress(AddressRequestDTO address) { this.address = address; }
}
