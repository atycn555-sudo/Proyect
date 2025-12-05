package com.example.vet.service;

import com.example.vet.model.Species;
import com.example.vet.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;

    public Species saveSpecies(Species species) {
        return speciesRepository.save(species);
    }

    public List<Species> findAllSpecies() {
        return speciesRepository.findAll();
    }

    public Optional<Species> findSpeciesById(Integer id) {
        return speciesRepository.findById(id);
    }

    public Optional<Species> updateSpecies(Integer id, Species speciesDetails) {
        return speciesRepository.findById(id)
                .map(existingSpecies -> {
                    existingSpecies.setSpeciesName(speciesDetails.getSpeciesName());
                    return speciesRepository.save(existingSpecies);
                });
    }

    public boolean deleteSpeciesById(Integer id) {
        if (speciesRepository.existsById(id)) {
            speciesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}