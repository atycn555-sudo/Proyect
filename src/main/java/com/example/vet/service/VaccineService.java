package com.example.vet.service;

import com.example.vet.dto.VaccineRequestDTO;
import com.example.vet.model.Pet;
import com.example.vet.model.Vaccine;
import com.example.vet.repository.PetRepository;
import com.example.vet.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private PetRepository petRepository;
    @Transactional
    public Vaccine saveVaccine(VaccineRequestDTO requestDTO) {

        Pet pet = petRepository.findById(requestDTO.getIdPet())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + requestDTO.getIdPet()));

        Vaccine newVaccine = new Vaccine();
        newVaccine.setVaccineName(requestDTO.getVaccineName());
        newVaccine.setApplicationDate(requestDTO.getApplicationDate());
        newVaccine.setNextVaccineDate(requestDTO.getNextVaccineDate());
        newVaccine.setBatchNumber(requestDTO.getBatchNumber());
        newVaccine.setPet(pet);
        return vaccineRepository.save(newVaccine);
    }


    public List<Vaccine> findAllVaccines() {
        return vaccineRepository.findAll();
    }


    public Optional<Vaccine> findVaccineById(Integer id) {
        return vaccineRepository.findById(id);
    }

    public List<Vaccine> findVaccinesByPetId(Integer petId) {
        return vaccineRepository.findByPetIdPet(petId);
    }

    public boolean deleteVaccineById(Integer id) {
        if (vaccineRepository.existsById(id)) {
            vaccineRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
