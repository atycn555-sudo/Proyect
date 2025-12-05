package com.example.vet.controller;

import com.example.vet.dto.VaccineRequestDTO;
import com.example.vet.dto.VaccineResponseDTO;
import com.example.vet.model.Vaccine;
import com.example.vet.service.VaccineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid; // <-- 1. IMPORTA ESTA ANOTACIÓN
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
@Tag(name = "Vaccines", description = "API para gestionar Registros de Vacunas")
@CrossOrigin(origins = "*")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Registrar una nueva vacuna para una mascota")
    @PostMapping
    public ResponseEntity<VaccineResponseDTO> createVaccine(@Valid @RequestBody VaccineRequestDTO requestDTO) { // <-- 2. AÑADE @Valid
        Vaccine newVaccine = vaccineService.saveVaccine(requestDTO);

        // --- 3. CONSTRUYE Y AÑADE LA CABECERA LOCATION ---
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVaccine.getIdVaccine())
                .toUri();
        
        return ResponseEntity.created(location).body(convertToDto(newVaccine));
    }

    @Operation(summary = "Obtener una lista de todos los registros de vacunas")
    @GetMapping
    @CrossOrigin(origins = "*") // <-- 4. AÑADE ESTA ANOTACIÓN PARA LA PRUEBA DE CORS
    public ResponseEntity<List<VaccineResponseDTO>> getAllVaccines() {
        List<Vaccine> vaccines = vaccineService.findAllVaccines();
        List<VaccineResponseDTO> dtos = vaccines.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // ... (El resto de los métodos se quedan igual, pero usando ResponseEntity para ser consistentes) ...
    
    @Operation(summary = "Obtener un registro de vacuna por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<VaccineResponseDTO> getVaccineById(@PathVariable Integer id) {
        return vaccineService.findVaccineById(id)
                .map(vaccine -> ResponseEntity.ok(convertToDto(vaccine)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los registros de vacunas de una mascota específica")
    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<VaccineResponseDTO>> getVaccinesByPetId(@PathVariable Integer petId) {
        List<Vaccine> vaccines = vaccineService.findVaccinesByPetId(petId);
        List<VaccineResponseDTO> dtos = vaccines.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Eliminar un registro de vacuna por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Integer id) {
        return vaccineService.deleteVaccineById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // --- Método de Conversión ---
    private VaccineResponseDTO convertToDto(Vaccine vaccine) {
        return modelMapper.map(vaccine, VaccineResponseDTO.class);
    }
}