package com.example.vet.dto;

import java.time.LocalDate;

public class PetResponseDTO {
    private Integer idPet;
    private String name;
    private LocalDate birthDate;
    private String breed;
    private ClientSimpleResponseDTO client;
    private SpeciesResponseDTO species;

    // Getters y Setters
    public Integer getIdPet() { return idPet; }
    public void setIdPet(Integer idPet) { this.idPet = idPet; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public ClientSimpleResponseDTO getClient() { return client; }
    public void setClient(ClientSimpleResponseDTO client) { this.client = client; }
    public SpeciesResponseDTO getSpecies() { return species; }
    public void setSpecies(SpeciesResponseDTO species) { this.species = species; }
}