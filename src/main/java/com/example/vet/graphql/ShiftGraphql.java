package com.example.vet.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.vet.dto.ShiftRequestDTO;
import com.example.vet.dto.ShiftResponseDTO;
import com.example.vet.model.Shift;
import com.example.vet.service.ShiftService;
import jakarta.validation.Valid;

@Controller
public class ShiftGraphql {

    @Autowired
    private ShiftService service;

    @QueryMapping
    public List<ShiftResponseDTO> getAllShifts() {
        return service.findAllShifts().stream()
                .map(this::toDTO)
                .toList();
    }

    @QueryMapping
    public ShiftResponseDTO getShiftById(@Argument Integer id) {
        return service.findShiftById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Shift not found with id " + id));
    }

    @QueryMapping
    public List<ShiftResponseDTO> getShiftsByVeterinarianId(@Argument Integer veterinarianId) {
        return service.findShiftsByVeterinarianId(veterinarianId).stream()
                .map(this::toDTO)
                .toList();
    }

    @MutationMapping
    public ShiftResponseDTO addShift(@Valid @Argument ShiftRequestDTO shiftDTO) {
        Shift saved = service.saveShift(shiftDTO);
        return toDTO(saved);
    }

    @MutationMapping
    public Boolean deleteShift(@Argument Integer id) {
        return service.deleteShiftById(id);
    }

    // -------------------
    // Conversión a DTO
    // -------------------
    private ShiftResponseDTO toDTO(Shift shift) {
        ShiftResponseDTO dto = new ShiftResponseDTO();
        dto.setIdShift(shift.getIdShift());
        dto.setDayOfWeek(shift.getDayOfWeek());
        dto.setStartTime(shift.getStartTime());
        dto.setEndTime(shift.getEndTime());

        com.example.vet.dto.VeterinarianSimpleResponseDTO vetDTO = new com.example.vet.dto.VeterinarianSimpleResponseDTO();
        vetDTO.setIdVeterinarian(shift.getVeterinarian().getIdVeterinarian());
        vetDTO.setFirstName(shift.getVeterinarian().getFirstName());
        vetDTO.setLastName(shift.getVeterinarian().getLastName());
        dto.setVeterinarian(vetDTO);

        return dto;
    }
}
