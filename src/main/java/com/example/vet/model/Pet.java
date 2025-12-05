package com.example.vet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Integer idPet;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "breed")
    private String breed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_species")
    private Species species;
    
    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<MedicalHistory> medicalHistories;

    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<Vaccine> vaccines;

    public Integer getIdPet() { return idPet; }
    public void setIdPet(Integer idPet) { this.idPet = idPet; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public Species getSpecies() { return species; }
    public void setSpecies(Species species) { this.species = species; }
    public List<MedicalHistory> getMedicalHistories() { return medicalHistories; }
    public void setMedicalHistories(List<MedicalHistory> medicalHistories) { this.medicalHistories = medicalHistories; }
    public List<Vaccine> getVaccines() { return vaccines; }
    public void setVaccines(List<Vaccine> vaccines) { this.vaccines = vaccines; }
}