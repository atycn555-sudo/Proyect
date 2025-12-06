package com.example.vet.controller;

import com.example.vet.dto.VaccineRequestDTO;
import com.example.vet.dto.VaccineResponseDTO;
import com.example.vet.model.Vaccine;
import com.example.vet.service.VaccineService;
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
@RequestMapping("/api/v1/vaccines")
@Tag(name = "Vaccines", description = "API for managing Vaccine Registries")
@CrossOrigin(origins = "*")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Register a new vaccine for a pet")
    @PostMapping
    public ResponseEntity<VaccineResponseDTO> createVaccine(@Valid @RequestBody VaccineRequestDTO requestDTO) {
        Vaccine newVaccine = vaccineService.saveVaccine(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVaccine.getIdVaccine())
                .toUri();
        
        return ResponseEntity.created(location).body(convertToDto(newVaccine));
    }

    @Operation(summary = "Obtain a list of all vaccine records")
    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<VaccineResponseDTO>> getAllVaccines() {
        List<Vaccine> vaccines = vaccineService.findAllVaccines();
        List<VaccineResponseDTO> dtos = vaccines.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    @Operation(summary = "Obtain a vaccination record using your ID")
    @GetMapping("/{id}")
    public ResponseEntity<VaccineResponseDTO> getVaccineById(@PathVariable Integer id) {
        return vaccineService.findVaccineById(id)
                .map(vaccine -> ResponseEntity.ok(convertToDto(vaccine)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtain all vaccination records for a specific pet")
    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<VaccineResponseDTO>> getVaccinesByPetId(@PathVariable Integer petId) {
        List<Vaccine> vaccines = vaccineService.findVaccinesByPetId(petId);
        List<VaccineResponseDTO> dtos = vaccines.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Delete a vaccine record by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Integer id) {
        return vaccineService.deleteVaccineById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private VaccineResponseDTO convertToDto(Vaccine vaccine) {
        return modelMapper.map(vaccine, VaccineResponseDTO.class);
    }
}
