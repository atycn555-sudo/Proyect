package com.example.vet.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.vet.dto.VaccineRequestDTO;
import com.example.vet.dto.VaccineResponseDTO;
import com.example.vet.model.Vaccine;
import com.example.vet.service.VaccineService;
import jakarta.validation.Valid;

@Controller
public class VaccineGraphql {

    @Autowired
    private VaccineService service;

    // -------------------
    // QUERIES
    // -------------------
    @QueryMapping
    public List<VaccineResponseDTO> getAllVaccines() {
        return service.findAllVaccines().stream()
                .map(this::toDTO)
                .toList();
    }

    @QueryMapping
    public VaccineResponseDTO getVaccineById(@Argument Integer id) {
        return service.findVaccineById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Vaccine not found with id " + id));
    }

    @QueryMapping
    public List<VaccineResponseDTO> getVaccinesByPetId(@Argument Integer petId) {
        return service.findVaccinesByPetId(petId).stream()
                .map(this::toDTO)
                .toList();
    }

    // -------------------
    // MUTATIONS
    // -------------------
    @MutationMapping
    public VaccineResponseDTO addVaccine(@Valid @Argument VaccineRequestDTO vaccineDTO) {
        Vaccine saved = service.saveVaccine(vaccineDTO);
        return toDTO(saved);
    }

    @MutationMapping
    public Boolean deleteVaccine(@Argument Integer id) {
        return service.deleteVaccineById(id);
    }

    // -------------------
    // Conversión a DTO
    // -------------------
    private VaccineResponseDTO toDTO(Vaccine vaccine) {
        VaccineResponseDTO dto = new VaccineResponseDTO();
        dto.setIdVaccine(vaccine.getIdVaccine());
        dto.setVaccineName(vaccine.getVaccineName());
        dto.setApplicationDate(vaccine.getApplicationDate());
        dto.setNextVaccineDate(vaccine.getNextVaccineDate());
        dto.setBatchNumber(vaccine.getBatchNumber());

        com.example.vet.dto.PetSimpleResponseDTO petDTO = new com.example.vet.dto.PetSimpleResponseDTO();
        petDTO.setIdPet(vaccine.getPet().getIdPet());
        petDTO.setName(vaccine.getPet().getName());
        dto.setPet(petDTO);

        return dto;
    }
}
