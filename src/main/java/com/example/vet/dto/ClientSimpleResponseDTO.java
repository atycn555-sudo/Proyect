package com.example.vet.dto;

public class ClientSimpleResponseDTO {
    private Integer idClient;
    private String firstName;
    private String lastName;

    // Getters y Setters
    public Integer getIdClient() { return idClient; }
    public void setIdClient(Integer idClient) { this.idClient = idClient; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}