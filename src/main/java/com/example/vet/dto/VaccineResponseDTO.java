package com.example.vet.dto;

import java.time.LocalDate;

public class VaccineResponseDTO {
    private Integer idVaccine;
    private String vaccineName;
    private LocalDate applicationDate;
    private LocalDate nextVaccineDate;
    private String batchNumber;
    private PetSimpleResponseDTO pet;

    // Getters y Setters manuales
    public Integer getIdVaccine() { return idVaccine; }
    public void setIdVaccine(Integer idVaccine) { this.idVaccine = idVaccine; }
    public String getVaccineName() { return vaccineName; }
    public void setVaccineName(String vaccineName) { this.vaccineName = vaccineName; }
    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }
    public LocalDate getNextVaccineDate() { return nextVaccineDate; }
    public void setNextVaccineDate(LocalDate nextVaccineDate) { this.nextVaccineDate = nextVaccineDate; }
    public String getBatchNumber() { return batchNumber; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }
    public PetSimpleResponseDTO getPet() { return pet; }
    public void setPet(PetSimpleResponseDTO pet) { this.pet = pet; }
}