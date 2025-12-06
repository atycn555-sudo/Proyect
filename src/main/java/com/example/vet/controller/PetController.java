package com.example.vet.controller;

import com.example.vet.dto.PetRequestDTO;
import com.example.vet.dto.PetResponseDTO;
import com.example.vet.model.Pet;
import com.example.vet.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/v1/pets")
@Tag(name = "Pets", description = "API for managing pets")
@CrossOrigin(origins = "*")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Create a new pet")
    @PostMapping
    public ResponseEntity<PetResponseDTO> createPet(@Valid @RequestBody PetRequestDTO requestDTO) {
        Pet newPet = petService.savePet(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPet.getIdPet())
                .toUri();
        
        return ResponseEntity.created(location).body(convertToDto(newPet));
    }

    @Operation(summary = "Get a list of all pets")
    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<PetResponseDTO>> getAllPets() {
        List<Pet> pets = petService.findAllPets();
        List<PetResponseDTO> dtos = pets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Update an existing pet")
    @PutMapping("/{id}")
    public ResponseEntity<PetResponseDTO> updatePet(@PathVariable Integer id, @Valid @RequestBody PetRequestDTO requestDTO) {
        return petService.updatePet(id, requestDTO)
                .map(updatedPet -> ResponseEntity.ok(convertToDto(updatedPet)))
                .orElse(ResponseEntity.notFound().build());
    }

    
    @Operation(summary = "Get a pet by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> getPetById(@PathVariable Integer id) {
        return petService.findPetById(id)
                .map(pet -> ResponseEntity.ok(convertToDto(pet)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Get all of a specific client's pets")
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<PetResponseDTO>> getPetsByClientId(@PathVariable Integer clientId) {
        List<Pet> pets = petService.findPetsByClientId(clientId);
        List<PetResponseDTO> dtos = pets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Delete a pet by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Integer id) {
        return petService.deletePetById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private PetResponseDTO convertToDto(Pet pet) {
        return modelMapper.map(pet, PetResponseDTO.class);
    }
}
