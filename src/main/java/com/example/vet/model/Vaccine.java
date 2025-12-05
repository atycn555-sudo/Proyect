package com.example.vet.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vaccine")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaccine")
    private Integer idVaccine;

    @Column(name = "vaccine_name", nullable = false)
    private String vaccineName;
    
    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;
    
    @Column(name = "next_vaccine_date")
    private LocalDate nextVaccineDate;

    @Column(name = "batch_number")
    private String batchNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pet", nullable = false)
    private Pet pet;

    public Integer getIdVaccine() {
        return idVaccine;
    }

    public void setIdVaccine(Integer idVaccine) {
        this.idVaccine = idVaccine;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDate getNextVaccineDate() {
        return nextVaccineDate;
    }

    public void setNextVaccineDate(LocalDate nextVaccineDate) {
        this.nextVaccineDate = nextVaccineDate;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}