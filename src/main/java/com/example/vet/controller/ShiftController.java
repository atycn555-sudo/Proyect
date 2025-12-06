package com.example.vet.controller;

import com.example.vet.dto.ShiftRequestDTO;
import com.example.vet.dto.ShiftResponseDTO;
import com.example.vet.model.Shift;
import com.example.vet.service.ShiftService;
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
@RequestMapping("/api/v1/shifts")
@Tag(name = "Shifts", description = "API for managing Veterinarian Shifts")
@CrossOrigin(origins = "*")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Create a new shift and assign it to a veterinarian")
    @PostMapping
    public ResponseEntity<ShiftResponseDTO> createShift(@Valid @RequestBody ShiftRequestDTO requestDTO) {
        Shift newShift = shiftService.saveShift(requestDTO);

        // --- 3. BUILD AND ADD THE LOCATION HEADER ---
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newShift.getIdShift())
                .toUri();

        return ResponseEntity.created(location).body(convertToDto(newShift));
    }

    @Operation(summary = "Get a list of all shifts")
    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ShiftResponseDTO>> getAllShifts() {
        List<Shift> shifts = shiftService.findAllShifts();
        List<ShiftResponseDTO> dtos = shifts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    @Operation(summary = "Get a shift by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ShiftResponseDTO> getShiftById(@PathVariable Integer id) {
        return shiftService.findShiftById(id)
                .map(shift -> ResponseEntity.ok(convertToDto(shift)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all shifts for a specific veterinarian")
    @GetMapping("/veterinarian/{veterinarianId}")
    public ResponseEntity<List<ShiftResponseDTO>> getShiftsByVeterinarianId(@PathVariable Integer veterinarianId) {
        List<Shift> shifts = shiftService.findShiftsByVeterinarianId(veterinarianId);
        List<ShiftResponseDTO> dtos = shifts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Delete a shift by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShift(@PathVariable Integer id) {
        return shiftService.deleteShiftById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private ShiftResponseDTO convertToDto(Shift shift) {
        return modelMapper.map(shift, ShiftResponseDTO.class);
    }
}
