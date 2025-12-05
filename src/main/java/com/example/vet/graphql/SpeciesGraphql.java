package com.example.vet.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.vet.dto.SpeciesRequestDTO;
import com.example.vet.dto.SpeciesResponseDTO;
import com.example.vet.model.Species;
import com.example.vet.service.SpeciesService;
import jakarta.validation.Valid;

@Controller
public class SpeciesGraphql {

    @Autowired
    private SpeciesService service;

    // -------------------
    // QUERIES
    // -------------------
    @QueryMapping
    public List<SpeciesResponseDTO> getAllSpecies() {
        return service.findAllSpecies().stream()
                .map(this::toDTO)
                .toList();
    }

    @QueryMapping
    public SpeciesResponseDTO getSpeciesById(@Argument Integer id) {
        return service.findSpeciesById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Species not found with id " + id));
    }

    // -------------------
    // MUTATIONS
    // -------------------
    @MutationMapping
    public SpeciesResponseDTO addSpecies(@Valid @Argument SpeciesRequestDTO speciesDTO) {
        Species saved = new Species();
        saved.setSpeciesName(speciesDTO.getSpeciesName());
        return toDTO(service.saveSpecies(saved));
    }

    @MutationMapping
    public SpeciesResponseDTO updateSpecies(@Argument Integer id, @Valid @Argument SpeciesRequestDTO speciesDTO) {
        Species speciesDetails = new Species();
        speciesDetails.setSpeciesName(speciesDTO.getSpeciesName());
        return service.updateSpecies(id, speciesDetails)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Species not found with id " + id));
    }

    @MutationMapping
    public Boolean deleteSpecies(@Argument Integer id) {
        return service.deleteSpeciesById(id);
    }

    // -------------------
    // Conversión a DTO
    // -------------------
    private SpeciesResponseDTO toDTO(Species species) {
        SpeciesResponseDTO dto = new SpeciesResponseDTO();
        dto.setIdSpecies(species.getIdSpecies());
        dto.setSpeciesName(species.getSpeciesName());
        return dto;
    }
}
