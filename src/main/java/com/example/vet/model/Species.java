package com.example.vet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_species")
    private Integer idSpecies;

    @Column(name = "species_name", nullable = false, unique = true, length = 50)
    private String speciesName;
    

    public Integer getIdSpecies() {
        return idSpecies;
    }

    public void setIdSpecies(Integer idSpecies) {
        this.idSpecies = idSpecies;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }
}