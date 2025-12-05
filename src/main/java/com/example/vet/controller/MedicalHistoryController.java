package com.example.vet.controller;

import com.example.vet.dto.MedicalHistoryRequestDTO;
import com.example.vet.dto.MedicalHistoryResponseDTO;
import com.example.vet.model.MedicalHistory;
import com.example.vet.service.MedicalHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/medical-history")
@Tag(name = "Medical History", description = "API para gestionar Historiales Médicos")
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @Operation(summary = "Crear un nuevo historial médico")
    @PostMapping
    public ResponseEntity<MedicalHistoryResponseDTO> createMedicalHistory(@Valid @RequestBody MedicalHistoryRequestDTO requestDTO) {
        MedicalHistory newHistory = medicalHistoryService.saveMedicalHistory(requestDTO);
        Optional<MedicalHistoryResponseDTO> responseDTO = medicalHistoryService.findMedicalHistoryById(newHistory.getIdHistory());
        return responseDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
                          .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Operation(summary = "Obtener todos los historiales médicos")
    @GetMapping
    public ResponseEntity<List<MedicalHistoryResponseDTO>> getAllMedicalHistories() {
        return new ResponseEntity<>(medicalHistoryService.findAllMedicalHistories(), HttpStatus.OK);
    }

    @Operation(summary = "Obtener un historial médico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistoryResponseDTO> getMedicalHistoryById(@PathVariable Integer id) {
        return medicalHistoryService.findMedicalHistoryById(id)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Buscar historiales por ID de mascota")
    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<MedicalHistoryResponseDTO>> getHistoriesByPetId(@PathVariable Integer petId) {
        return new ResponseEntity<>(medicalHistoryService.findByPetId(petId), HttpStatus.OK);
    }

    @Operation(summary = "Buscar historiales por ID de veterinario")
    @GetMapping("/vet/{vetId}")
    public ResponseEntity<List<MedicalHistoryResponseDTO>> getHistoriesByVeterinarianId(@PathVariable Integer vetId) {
        return new ResponseEntity<>(medicalHistoryService.findByVeterinarianId(vetId), HttpStatus.OK);
    }

    @Operation(summary = "Buscar historiales por fecha")
    @GetMapping("/date/{date}")
    public ResponseEntity<List<MedicalHistoryResponseDTO>> getHistoriesByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return new ResponseEntity<>(medicalHistoryService.findByDate(date), HttpStatus.OK);
    }

    @Operation(summary = "Actualizar un historial médico existente")
    @PutMapping("/{id}")
    public ResponseEntity<MedicalHistoryResponseDTO> updateMedicalHistory(@PathVariable Integer id,
                                                                           @Valid @RequestBody MedicalHistoryRequestDTO requestDTO) {
        return medicalHistoryService.updateMedicalHistory(id, requestDTO)
                .flatMap(updated -> medicalHistoryService.findMedicalHistoryById(updated.getIdHistory()))
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Eliminar un historial médico por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalHistory(@PathVariable Integer id) {
        return medicalHistoryService.deleteMedicalHistoryById(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
