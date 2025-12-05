package com.example.vet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SupplierRequestDTO {

    @NotBlank(message = "El nombre del proveedor no puede estar vacío")
    @Size(max = 150, message = "El nombre no puede exceder los 150 caracteres")
    private String name;

    @Size(max = 100, message = "El nombre de contacto no puede exceder los 100 caracteres")
    private String contactPerson;

    @Size(max = 20, message = "El teléfono no puede exceder los 20 caracteres")
    private String phone;

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}