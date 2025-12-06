package com.example.vet.dto;

public class VeterinarianResponseDTO {
    private Integer idVeterinarian;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String phone;
    private String email;

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

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
