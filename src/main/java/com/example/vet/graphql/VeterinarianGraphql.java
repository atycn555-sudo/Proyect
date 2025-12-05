package com.example.vet.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.vet.dto.VeterinarianRequestDTO;
import com.example.vet.dto.VeterinarianResponseDTO;
import com.example.vet.model.Veterinarian;
import com.example.vet.service.VeterinarianService;
import jakarta.validation.Valid;

@Controller
public class VeterinarianGraphql {

    @Autowired
    private VeterinarianService service;

    // -------------------
    // QUERIES
    // -------------------
    @QueryMapping
    public List<VeterinarianResponseDTO> getAllVeterinarians() {
        return service.findAllVeterinarians().stream()
                .map(this::toDTO)
                .toList();
    }

    @QueryMapping
    public VeterinarianResponseDTO getVeterinarianById(@Argument Integer id) {
        return service.findVeterinarianById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Veterinarian not found with id " + id));
    }

    @QueryMapping
    public VeterinarianResponseDTO getVeterinarianByLicenseNumber(@Argument String licenseNumber) {
        return service.findByLicenseNumber(licenseNumber)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Veterinarian not found with license " + licenseNumber));
    }

    @QueryMapping
    public VeterinarianResponseDTO getVeterinarianByEmail(@Argument String email) {
        return service.findByEmail(email)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Veterinarian not found with email " + email));
    }

    // -------------------
    // MUTATIONS
    // -------------------
    @MutationMapping
    public VeterinarianResponseDTO addVeterinarian(@Valid @Argument VeterinarianRequestDTO vetDTO) {
        Veterinarian vet = new Veterinarian();
        vet.setFirstName(vetDTO.getFirstName());
        vet.setLastName(vetDTO.getLastName());
        vet.setLicenseNumber(vetDTO.getLicenseNumber());
        vet.setPhone(vetDTO.getPhone());
        vet.setEmail(vetDTO.getEmail());

        return toDTO(service.saveVeterinarian(vet));
    }

    @MutationMapping
    public VeterinarianResponseDTO updateVeterinarian(@Argument Integer id,
            @Valid @Argument VeterinarianRequestDTO vetDTO) {
        Veterinarian vetDetails = new Veterinarian();
        vetDetails.setFirstName(vetDTO.getFirstName());
        vetDetails.setLastName(vetDTO.getLastName());
        vetDetails.setLicenseNumber(vetDTO.getLicenseNumber());
        vetDetails.setPhone(vetDTO.getPhone());
        vetDetails.setEmail(vetDTO.getEmail());

        return service.updateVeterinarian(id, vetDetails)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Veterinarian not found with id " + id));
    }

    @MutationMapping
    public Boolean deleteVeterinarian(@Argument Integer id) {
        return service.deleteVeterinarianById(id);
    }

    // -------------------
    // Conversión a DTO
    // -------------------
    private VeterinarianResponseDTO toDTO(Veterinarian vet) {
        VeterinarianResponseDTO dto = new VeterinarianResponseDTO();
        dto.setIdVeterinarian(vet.getIdVeterinarian());
        dto.setFirstName(vet.getFirstName());
        dto.setLastName(vet.getLastName());
        dto.setLicenseNumber(vet.getLicenseNumber());
        dto.setPhone(vet.getPhone());
        dto.setEmail(vet.getEmail());
        return dto;
    }
}
