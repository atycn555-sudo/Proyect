package com.example.vet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class PetRequestDTO {

    @NotBlank(message = "El nombre de la mascota no puede estar vacío")
    @Size(max = 100)
    private String name;

    @PastOrPresent(message = "La fecha de nacimiento no puede ser en el futuro")
    private LocalDate birthDate;

    private String breed;

    @NotNull(message = "El ID del cliente no puede ser nulo")
    private Integer idClient; 

    @NotNull(message = "El ID de la especie no puede ser nulo")
    private Integer idSpecies;

    // Getters y Setters
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