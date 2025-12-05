package com.example.vet.service;

import com.example.vet.model.Veterinarian;
import com.example.vet.repository.VeterinarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarianService {

    @Autowired
    private VeterinarianRepository veterinarianRepository;

    public Veterinarian saveVeterinarian(Veterinarian veterinarian) {
        return veterinarianRepository.save(veterinarian);
    }

    public List<Veterinarian> findAllVeterinarians() {
        return veterinarianRepository.findAll();
    }


    public Optional<Veterinarian> findVeterinarianById(Integer id) {
        return veterinarianRepository.findById(id);
    }

    public Optional<Veterinarian> findByLicenseNumber(String licenseNumber) {
        return veterinarianRepository.findByLicenseNumber(licenseNumber);
    }

    public Optional<Veterinarian> findByEmail(String email) {
        return veterinarianRepository.findByEmail(email);
    }


    public Optional<Veterinarian> updateVeterinarian(Integer id, Veterinarian vetDetails) {
        return veterinarianRepository.findById(id)
                .map(existingVet -> {
                    existingVet.setFirstName(vetDetails.getFirstName());
                    existingVet.setLastName(vetDetails.getLastName());
                    existingVet.setLicenseNumber(vetDetails.getLicenseNumber());
                    existingVet.setPhone(vetDetails.getPhone());
                    existingVet.setEmail(vetDetails.getEmail());
                    return veterinarianRepository.save(existingVet);
                });
    }

    public boolean deleteVeterinarianById(Integer id) {
        if (veterinarianRepository.existsById(id)) {
            veterinarianRepository.deleteById(id);
            return true;
        }
        return false;
    }
}