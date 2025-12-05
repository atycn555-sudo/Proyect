package com.example.vet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class VaccineRequestDTO {

    @NotBlank(message = "El nombre de la vacuna no puede estar vacío")
    private String vaccineName;

    @NotNull(message = "La fecha de aplicación no puede ser nula")
    private LocalDate applicationDate;

    private LocalDate nextVaccineDate;
    private String batchNumber;

    @NotNull(message = "El ID de la mascota no puede ser nulo")
    private Integer idPet;

    // Getters y Setters manuales
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