package com.example.vet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class VaccineRequestDTO {

    @NotBlank(message = "The vaccine name cannot be empty.")
    private String vaccineName;

    @NotNull(message = "The application date cannot be zero")
    private LocalDate applicationDate;

    private LocalDate nextVaccineDate;
    private String batchNumber;

    @NotNull(message = "The pet ID cannot be null")
    private Integer idPet;

    public String getVaccineName() { return vaccineName; }
    public void setVaccineName(String vaccineName) { this.vaccineName = vaccineName; }
    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }
    public LocalDate getNextVaccineDate() { return nextVaccineDate; }
    public void setNextVaccineDate(LocalDate nextVaccineDate) { this.nextVaccineDate = nextVaccineDate; }
    public String getBatchNumber() { return batchNumber; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }
    public Integer getIdPet() { return idPet; }
    public void setIdPet(Integer idPet) { this.idPet = idPet; }
}
