package com.example.vet.dto;

public class VeterinarianSimpleResponseDTO {
    private Integer idVeterinarian;
    private String firstName;
    private String lastName;

    // Getters y Setters
    public Integer getIdVeterinarian() {
        return idVeterinarian;
    }

    public void setIdVeterinarian(Integer idVeterinarian) {
        this.idVeterinarian = idVeterinarian;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}