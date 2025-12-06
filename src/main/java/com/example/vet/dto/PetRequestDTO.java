package com.example.vet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class PetRequestDTO {

    @NotBlank(message = "The pet's name cannot be empty.")
    @Size(max = 100)
    private String name;

    @PastOrPresent(message = "The date of birth cannot be in the future")
    private LocalDate birthDate;

    private String breed;

    @NotNull(message = "The client ID cannot be null")
    private Integer idClient; 

    @NotNull(message = "The species ID cannot be null")
    private Integer idSpecies;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public Integer getIdClient() { return idClient; }
    public void setIdClient(Integer idClient) { this.idClient = idClient; }
    public Integer getIdSpecies() { return idSpecies; }
    public void setIdSpecies(Integer idSpecies) { this.idSpecies = idSpecies; }
}
