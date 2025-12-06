package com.example.vet.controller;

import com.example.vet.dto.SupplierRequestDTO;
import com.example.vet.dto.SupplierResponseDTO;
import com.example.vet.model.Supplier;
import com.example.vet.service.SupplierService;
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
@RequestMapping("/api/v1/suppliers")
@Tag(name = "Suppliers", description = "API for managing Suppliers")
@CrossOrigin(origins = "*")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@Valid @RequestBody SupplierRequestDTO requestDTO) {
        Supplier newSupplier = supplierService.saveSupplier(requestDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newSupplier.getIdSupplier()).toUri();
        return ResponseEntity.created(location).body(modelMapper.map(newSupplier, SupplierResponseDTO.class));
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.findAllSuppliers();
        return ResponseEntity.ok(suppliers.stream().map(s -> modelMapper.map(s, SupplierResponseDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable Integer id) {
        return supplierService.findSupplierById(id)
                .map(supplier -> ResponseEntity.ok(modelMapper.map(supplier, SupplierResponseDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable Integer id, @Valid @RequestBody SupplierRequestDTO requestDTO) {
        return supplierService.updateSupplier(id, requestDTO)
                .map(updatedSupplier -> ResponseEntity.ok(modelMapper.map(updatedSupplier, SupplierResponseDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Integer id) {
        return supplierService.deleteSupplierById(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
