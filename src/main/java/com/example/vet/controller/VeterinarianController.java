package com.example.vet.controller;

import com.example.vet.dto.VeterinarianRequestDTO;
import com.example.vet.dto.VeterinarianResponseDTO;
import com.example.vet.model.Veterinarian;
import com.example.vet.service.VeterinarianService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid; // <-- 1. IMPORT THIS
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/veterinarians")
@Tag(name = "Veterinarians", description = "API for managing Veterinarians")
@CrossOrigin(origins = "*")
public class VeterinarianController {

    @Autowired
    private VeterinarianService veterinarianService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Create a new veterinarian")
    @PostMapping
    public ResponseEntity<VeterinarianResponseDTO> createVeterinarian(@Valid @RequestBody VeterinarianRequestDTO requestDTO) { // <-- 2. ADD @Valid
        Veterinarian veterinarian = modelMapper.map(requestDTO, Veterinarian.class);
        Veterinarian newVeterinarian = veterinarianService.saveVeterinarian(veterinarian);

        // 3. BUILD AND RETURN THE LOCATION HEADER
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVeterinarian.getIdVeterinarian())
                .toUri();

        return ResponseEntity.created(location).body(modelMapper.map(newVeterinarian, VeterinarianResponseDTO.class));
    }

    @Operation(summary = "Get a list of all veterinarians")
    @GetMapping
    @CrossOrigin(origins = "*") // <-- 4. ADD THIS FOR THE CORS TEST
    public ResponseEntity<List<VeterinarianResponseDTO>> getAllVeterinarians() {
        List<Veterinarian> veterinarians = veterinarianService.findAllVeterinarians();
        List<VeterinarianResponseDTO> dtos = veterinarians.stream()
                .map(vet -> modelMapper.map(vet, VeterinarianResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Get a veterinarian by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<VeterinarianResponseDTO> getVeterinarianById(@PathVariable Integer id) {
        return veterinarianService.findVeterinarianById(id)
                .map(vet -> ResponseEntity.ok(modelMapper.map(vet, VeterinarianResponseDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Find a veterinarian by their license number")
    @GetMapping("/license/{licenseNumber}")
    public ResponseEntity<VeterinarianResponseDTO> getVeterinarianByLicenseNumber(@PathVariable String licenseNumber) {
        return veterinarianService.findByLicenseNumber(licenseNumber)
                .map(vet -> ResponseEntity.ok(modelMapper.map(vet, VeterinarianResponseDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing veterinarian")
    @PutMapping("/{id}")
    public ResponseEntity<VeterinarianResponseDTO> updateVeterinarian(@PathVariable Integer id, @Valid @RequestBody VeterinarianRequestDTO requestDTO) { // <-- 2. ADD @Valid
        Veterinarian vetDetails = modelMapper.map(requestDTO, Veterinarian.class);
        return veterinarianService.updateVeterinarian(id, vetDetails)
                .map(updatedVet -> ResponseEntity.ok(modelMapper.map(updatedVet, VeterinarianResponseDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeterinarian(@PathVariable Integer id) {
        return veterinarianService.deleteVeterinarianById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}