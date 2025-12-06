package com.example.vet.controller;

import com.example.vet.dto.SpeciesRequestDTO;
import com.example.vet.dto.SpeciesResponseDTO;
import com.example.vet.model.Species;
import com.example.vet.service.SpeciesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/species")
@Tag(name = "Species", description = "API para gestionar Especies de mascotas")
@CrossOrigin(origins = "*")
public class SpeciesController {

    @Autowired
    private SpeciesService speciesService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<SpeciesResponseDTO> createSpecies(@Valid @RequestBody SpeciesRequestDTO requestDTO) {
        Species species = modelMapper.map(requestDTO, Species.class);
        Species newSpecies = speciesService.saveSpecies(species);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newSpecies.getIdSpecies())
                .toUri();
        
        return ResponseEntity.created(location).body(convertToDto(newSpecies));
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<SpeciesResponseDTO>> getAllSpecies() {
        List<Species> speciesList = speciesService.findAllSpecies();
        List<SpeciesResponseDTO> dtos = speciesList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpeciesResponseDTO> getSpeciesById(@PathVariable Integer id) {
        return speciesService.findSpeciesById(id)
                .map(species -> ResponseEntity.ok(convertToDto(species)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpeciesResponseDTO> updateSpecies(@PathVariable Integer id, @Valid @RequestBody SpeciesRequestDTO requestDTO) {
        Species speciesDetails = modelMapper.map(requestDTO, Species.class);
        return speciesService.updateSpecies(id, speciesDetails)
                .map(updatedSpecies -> ResponseEntity.ok(convertToDto(updatedSpecies)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecies(@PathVariable Integer id) {
        return speciesService.deleteSpeciesById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
    
    private SpeciesResponseDTO convertToDto(Species species) {
        return modelMapper.map(species, SpeciesResponseDTO.class);
    }
}
