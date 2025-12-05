package com.example.vet.service;

import com.example.vet.dto.ShiftRequestDTO;
import com.example.vet.model.Shift;
import com.example.vet.model.Veterinarian;
import com.example.vet.repository.ShiftRepository;
import com.example.vet.repository.VeterinarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private VeterinarianRepository veterinarianRepository;
    @Transactional
    public Shift saveShift(ShiftRequestDTO requestDTO) {
        Veterinarian veterinarian = veterinarianRepository.findById(requestDTO.getIdVeterinarian())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con id: " + requestDTO.getIdVeterinarian()));


        Shift newShift = new Shift();
        newShift.setDayOfWeek(requestDTO.getDayOfWeek());
        newShift.setStartTime(requestDTO.getStartTime());
        newShift.setEndTime(requestDTO.getEndTime());
        newShift.setVeterinarian(veterinarian);
        return shiftRepository.save(newShift);
    }

    public List<Shift> findAllShifts() {
        return shiftRepository.findAll();
    }

 
    public Optional<Shift> findShiftById(Integer id) {
        return shiftRepository.findById(id);
    }

    /**
     * Busca todos los turnos de un veterinario específico.
     * @param veterinarianId El ID del veterinario.
     * @return La lista de sus turnos.
     */
    public List<Shift> findShiftsByVeterinarianId(Integer veterinarianId) {
        return shiftRepository.findByVeterinarianIdVeterinarian(veterinarianId);
    }

    /**
     * Elimina un turno por su ID.
     * @param id El ID del turno a eliminar.
     * @return true si se eliminó, false si no.
     */
    public boolean deleteShiftById(Integer id) {
        if (shiftRepository.existsById(id)) {
            shiftRepository.deleteById(id);
            return true;
        }
        return false;
    }
}