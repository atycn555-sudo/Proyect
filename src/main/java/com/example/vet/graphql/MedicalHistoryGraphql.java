package com.example.vet.graphql;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.vet.dto.MedicalHistoryRequestDTO;
import com.example.vet.dto.MedicalHistoryResponseDTO;
import com.example.vet.service.MedicalHistoryService;
import jakarta.validation.Valid;

@Controller
public class MedicalHistoryGraphql {

    @Autowired
    private MedicalHistoryService service;

    @QueryMapping
    public List<MedicalHistoryResponseDTO> getAllMedicalHistories() {
        return service.findAllMedicalHistories();
    }

    @QueryMapping
    public MedicalHistoryResponseDTO getMedicalHistoryById(@Argument Integer id) {
        return service.findMedicalHistoryById(id)
                .orElseThrow(() -> new RuntimeException("Medical history not found with id " + id));
    }

    @QueryMapping
    public List<MedicalHistoryResponseDTO> getHistoriesByPetId(@Argument Integer petId) {
        return service.findByPetId(petId);
    }

    @QueryMapping
    public List<MedicalHistoryResponseDTO> getHistoriesByVeterinarianId(@Argument Integer vetId) {
        return service.findByVeterinarianId(vetId);
    }

    @QueryMapping
    public List<MedicalHistoryResponseDTO> getHistoriesByDate(@Argument String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return service.findByDate(parsedDate);
    }

    @MutationMapping
    public MedicalHistoryResponseDTO addMedicalHistory(@Valid @Argument MedicalHistoryRequestDTO historyDTO) {
        return service.toDTO(service.saveMedicalHistory(historyDTO));
    }

    @MutationMapping
    public MedicalHistoryResponseDTO updateMedicalHistory(@Argument Integer id,
            @Valid @Argument MedicalHistoryRequestDTO historyDTO) {
        return service.updateMedicalHistory(id, historyDTO)
                .map(service::toDTO)
                .orElseThrow(() -> new RuntimeException("Medical history not found with id " + id));
    }

    @MutationMapping
    public Boolean deleteMedicalHistory(@Argument Integer id) {
        return service.deleteMedicalHistoryById(id);
    }
}
