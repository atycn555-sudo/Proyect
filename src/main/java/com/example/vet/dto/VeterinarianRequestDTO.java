package com.example.vet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VeterinarianRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100)
    private String firstName;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100)
    private String lastName;

    @NotBlank(message = "El número de licencia no puede estar vacío")
    @Size(max = 50)
    private String licenseNumber;

    private String phone;

    @NotBlank(message = "El email не puede estar vacío")
    @Email(message = "El formato del email es inválido")
    private String email;

    // Getters y Setters manuales
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}