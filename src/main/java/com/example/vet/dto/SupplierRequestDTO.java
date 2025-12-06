package com.example.vet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SupplierRequestDTO {

    @NotBlank(message = "The supplier name cannot be empty.")
    @Size(max = 150, message = "The name cannot exceed 150 characters")
    private String name;

    @Size(max = 100, message = "The contact name cannot exceed 100 characters")
    private String contactPerson;

    @Size(max = 20, message = "The phone number cannot exceed 20 characters.")
    private String phone;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
