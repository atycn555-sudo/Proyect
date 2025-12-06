package com.example.vet.service;

import com.example.vet.dto.MedicalHistoryRequestDTO;
import com.example.vet.dto.MedicalHistoryResponseDTO;
import com.example.vet.dto.PetSimpleResponseDTO;
import com.example.vet.dto.VeterinarianSimpleResponseDTO;
import com.example.vet.model.MedicalHistory;
import com.example.vet.model.Pet;
import com.example.vet.model.Veterinarian;
import com.example.vet.repository.MedicalHistoryRepository;
import com.example.vet.repository.PetRepository;
import com.example.vet.repository.VeterinarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicalHistoryService {

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private VeterinarianRepository veterinarianRepository;

    @Transactional
    public MedicalHistory saveMedicalHistory(MedicalHistoryRequestDTO dto) {
        Pet pet = petRepository.findById(dto.getIdPet())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + dto.getIdPet()));

        Veterinarian vet = veterinarianRepository.findById(dto.getIdVeterinarian())
                .orElseThrow(
                        () -> new RuntimeException("Veterinario no encontrado con id: " + dto.getIdVeterinarian()));

        MedicalHistory history = new MedicalHistory();
        history.setDate(dto.getDate());
        history.setDiagnosis(dto.getDiagnosis());
        history.setTreatment(dto.getTreatment());
        history.setPet(pet);
        history.setVeterinarian(vet);

        return medicalHistoryRepository.save(history);
    }

    public List<MedicalHistoryResponseDTO> findAllMedicalHistories() {
        return medicalHistoryRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<MedicalHistoryResponseDTO> findMedicalHistoryById(Integer id) {
        return medicalHistoryRepository.findById(id)
                .map(this::toDTO);
    }

    public List<MedicalHistoryResponseDTO> findByPetId(Integer petId) {
        return medicalHistoryRepository.findAll().stream()
                .filter(h -> h.getPet().getIdPet().equals(petId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MedicalHistoryResponseDTO> findByVeterinarianId(Integer vetId) {
        return medicalHistoryRepository.findAll().stream()
                .filter(h -> h.getVeterinarian().getIdVeterinarian().equals(vetId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MedicalHistoryResponseDTO> findByDate(LocalDate date) {
        return medicalHistoryRepository.findAll().stream()
                .filter(h -> h.getDate().equals(date))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<MedicalHistory> updateMedicalHistory(Integer id, MedicalHistoryRequestDTO dto) {
        return medicalHistoryRepository.findById(id)
                .map(existing -> {
                    existing.setDate(dto.getDate());
                    existing.setDiagnosis(dto.getDiagnosis());
                    existing.setTreatment(dto.getTreatment());

                    if (!existing.getPet().getIdPet().equals(dto.getIdPet())) {
                        Pet pet = petRepository.findById(dto.getIdPet())
                                .orElseThrow(
                                        () -> new RuntimeException("Mascota no encontrada con id: " + dto.getIdPet()));
                        existing.setPet(pet);
                    }

                    if (!existing.getVeterinarian().getIdVeterinarian().equals(dto.getIdVeterinarian())) {
                        Veterinarian vet = veterinarianRepository.findById(dto.getIdVeterinarian())
                                .orElseThrow(() -> new RuntimeException(
                                        "Veterinario no encontrado con id: " + dto.getIdVeterinarian()));
                        existing.setVeterinarian(vet);
                    }

                    return medicalHistoryRepository.save(existing);
                });
    }

    public boolean deleteMedicalHistoryById(Integer id) {
        if (medicalHistoryRepository.existsById(id)) {
            medicalHistoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public MedicalHistoryResponseDTO toDTO(MedicalHistory history) {
        MedicalHistoryResponseDTO dto = new MedicalHistoryResponseDTO();
        dto.setIdHistory(history.getIdHistory());
        dto.setDate(history.getDate());
        dto.setDiagnosis(history.getDiagnosis());
        dto.setTreatment(history.getTreatment());

        PetSimpleResponseDTO petDTO = new PetSimpleResponseDTO();
        petDTO.setIdPet(history.getPet().getIdPet());
        petDTO.setName(history.getPet().getName());
        dto.setPet(petDTO);

        VeterinarianSimpleResponseDTO vetDTO = new VeterinarianSimpleResponseDTO();
        vetDTO.setIdVeterinarian(history.getVeterinarian().getIdVeterinarian());
        vetDTO.setFirstName(history.getVeterinarian().getFirstName());
        vetDTO.setLastName(history.getVeterinarian().getLastName());
        dto.setVeterinarian(vetDTO);

        return dto;
    }
}
