package com.example.vet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SpeciesRequestDTO {

    @NotBlank(message = "The species name cannot be empty")
    @Size(max = 50, message = "The species name cannot exceed 50 characters")
    private String speciesName;

    // Getters and Setters
    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }
}