package com.example.vet.controller;

import com.example.vet.dto.ClinicServiceRequestDTO;
import com.example.vet.dto.ClinicServiceResponseDTO;
import com.example.vet.model.ClinicService;
import com.example.vet.service.ClinicServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/services")
@Tag(name = "Clinic Services", description = "API para gestionar los Servicios de la Clínica")
public class ClinicServiceController {

    @Autowired
    private ClinicServiceService clinicServiceService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Crear un nuevo servicio")
    @PostMapping
    public ResponseEntity<ClinicServiceResponseDTO> createClinicService(@RequestBody ClinicServiceRequestDTO requestDTO) {
        ClinicService clinicService = modelMapper.map(requestDTO, ClinicService.class);
        ClinicService newClinicService = clinicServiceService.saveClinicService(clinicService);
        return new ResponseEntity<>(modelMapper.map(newClinicService, ClinicServiceResponseDTO.class), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una lista de todos los servicios")
    @GetMapping
    public ResponseEntity<List<ClinicServiceResponseDTO>> getAllClinicServices() {
        List<ClinicService> services = clinicServiceService.findAllClinicServices();
        List<ClinicServiceResponseDTO> dtos = services.stream()
                .map(service -> modelMapper.map(service, ClinicServiceResponseDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Operation(summary = "Obtener un servicio por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClinicServiceResponseDTO> getClinicServiceById(@PathVariable Integer id) {
        return clinicServiceService.findClinicServiceById(id)
                .map(service -> new ResponseEntity<>(modelMapper.map(service, ClinicServiceResponseDTO.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Buscar servicios por nombre")
    @GetMapping("/search/{name}")
    public ResponseEntity<List<ClinicServiceResponseDTO>> getClinicServicesByName(@PathVariable String name) {
        List<ClinicService> services = clinicServiceService.findClinicServicesByName(name);
        List<ClinicServiceResponseDTO> dtos = services.stream()
                .map(service -> modelMapper.map(service, ClinicServiceResponseDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Operation(summary = "Actualizar un servicio existente")
    @PutMapping("/{id}")
    public ResponseEntity<ClinicServiceResponseDTO> updateClinicService(@PathVariable Integer id, @RequestBody ClinicServiceRequestDTO requestDTO) {
        ClinicService serviceDetails = modelMapper.map(requestDTO, ClinicService.class);
        return clinicServiceService.updateClinicService(id, serviceDetails)
                .map(updatedService -> new ResponseEntity<>(modelMapper.map(updatedService, ClinicServiceResponseDTO.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinicService(@PathVariable Integer id) {
        return clinicServiceService.deleteClinicServiceById(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}