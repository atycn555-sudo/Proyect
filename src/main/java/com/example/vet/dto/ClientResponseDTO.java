package com.example.vet.dto;

import java.util.List;

public class ClientResponseDTO {
    private Integer idClient;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private AddressResponseDTO address;
    private List<PetSimpleResponseDTO> pets;
    
    // Getters y Setters
    public Integer getIdClient() { return idClient; }
    public void setIdClient(Integer idClient) { this.idClient = idClient; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public AddressResponseDTO getAddress() { return address; }
    public void setAddress(AddressResponseDTO address) { this.address = address; }
    public List<PetSimpleResponseDTO> getPets() { return pets; }
    public void setPets(List<PetSimpleResponseDTO> pets) { this.pets = pets; }
}