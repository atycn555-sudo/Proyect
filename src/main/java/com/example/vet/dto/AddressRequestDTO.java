package com.example.vet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddressRequestDTO {

    @NotBlank(message = "La calle no puede estar vacía")
    @Size(max = 100, message = "La calle no puede tener más de 100 caracteres")
    private String street;

    @Size(max = 20, message = "El número exterior no puede tener más de 20 caracteres")
    private String externalNumber;

    @Size(max = 100, message = "La colonia no puede tener más de 100 caracteres")
    private String neighborhood;

    @NotBlank(message = "La ciudad no puede estar vacía")
    @Size(max = 100, message = "La ciudad no puede tener más de 100 caracteres")
    private String city;

    @Size(max = 10, message = "El código postal no puede tener más de 10 caracteres")
    private String zipCode;

    // ... tus getters y setters manuales ...
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