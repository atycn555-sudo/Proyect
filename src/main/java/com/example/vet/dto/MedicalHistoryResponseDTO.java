package com.example.vet.dto;

import java.time.LocalDate;

public class MedicalHistoryResponseDTO {
    private Integer idHistory;
    private LocalDate date;
    private String diagnosis;
    private String treatment;
    private PetSimpleResponseDTO pet;
    private VeterinarianSimpleResponseDTO veterinarian;

    public Integer getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Integer idHistory) {
        this.idHistory = idHistory;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public PetSimpleResponseDTO getPet() {
        return pet;
    }

    public void setPet(PetSimpleResponseDTO pet) {
        this.pet = pet;
    }

    public VeterinarianSimpleResponseDTO getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(VeterinarianSimpleResponseDTO veterinarian) {
        this.veterinarian = veterinarian;
    }
}
