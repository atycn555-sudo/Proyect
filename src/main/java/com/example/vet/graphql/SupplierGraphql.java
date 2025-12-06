package com.example.vet.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.vet.dto.SupplierRequestDTO;
import com.example.vet.dto.SupplierResponseDTO;
import com.example.vet.model.Supplier;
import com.example.vet.service.SupplierService;
import jakarta.validation.Valid;

@Controller
public class SupplierGraphql {

    @Autowired
    private SupplierService service;

    @QueryMapping
    public List<SupplierResponseDTO> getAllSuppliers() {
        return service.findAllSuppliers().stream()
                .map(this::toDTO)
                .toList();
    }

    @QueryMapping
    public SupplierResponseDTO getSupplierById(@Argument Integer id) {
        return service.findSupplierById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id " + id));
    }

    @MutationMapping
    public SupplierResponseDTO addSupplier(@Valid @Argument SupplierRequestDTO supplierDTO) {
        Supplier saved = service.saveSupplier(supplierDTO);
        return toDTO(saved);
    }

    @MutationMapping
    public SupplierResponseDTO updateSupplier(@Argument Integer id, @Valid @Argument SupplierRequestDTO supplierDTO) {
        return service.updateSupplier(id, supplierDTO)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id " + id));
    }

    @MutationMapping
    public Boolean deleteSupplier(@Argument Integer id) {
        return service.deleteSupplierById(id);
    }

    private SupplierResponseDTO toDTO(Supplier supplier) {
        SupplierResponseDTO dto = new SupplierResponseDTO();
        dto.setIdSupplier(supplier.getIdSupplier());
        dto.setName(supplier.getName());
        dto.setContactPerson(supplier.getContactPerson());
        dto.setPhone(supplier.getPhone());
        return dto;
    }
}
