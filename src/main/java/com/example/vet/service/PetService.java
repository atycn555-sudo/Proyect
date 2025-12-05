package com.example.vet.service;

import com.example.vet.dto.PetRequestDTO;
import com.example.vet.model.Client;
import com.example.vet.model.Pet;
import com.example.vet.model.Species;
import com.example.vet.repository.ClientRepository;
import com.example.vet.repository.PetRepository;
import com.example.vet.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SpeciesRepository speciesRepository;

    @Transactional
    public Pet savePet(PetRequestDTO requestDTO) {
        Client client = clientRepository.findById(requestDTO.getIdClient())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + requestDTO.getIdClient()));

        Species species = speciesRepository.findById(requestDTO.getIdSpecies())
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con id: " + requestDTO.getIdSpecies()));

        Pet newPet = new Pet();
        newPet.setName(requestDTO.getName());
        newPet.setBirthDate(requestDTO.getBirthDate());
        newPet.setBreed(requestDTO.getBreed());
        newPet.setClient(client);
        newPet.setSpecies(species);

        return petRepository.save(newPet);
    }

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> findPetById(Integer id) {
        return petRepository.findById(id);
    }

    public List<Pet> findPetsByClientId(Integer clientId) {
        return petRepository.findByClientIdClient(clientId);
    }

    @Transactional
    public Optional<Pet> updatePet(Integer id, PetRequestDTO requestDTO) {
        return petRepository.findById(id)
            .map(existingPet -> {
                existingPet.setName(requestDTO.getName());
                existingPet.setBirthDate(requestDTO.getBirthDate());
                existingPet.setBreed(requestDTO.getBreed());

                if (requestDTO.getIdClient() != null && !requestDTO.getIdClient().equals(existingPet.getClient().getIdClient())) {
                    Client newClient = clientRepository.findById(requestDTO.getIdClient())
                            .orElseThrow(() -> new RuntimeException("Nuevo cliente no encontrado con id: " + requestDTO.getIdClient()));
                    existingPet.setClient(newClient);
                }

                if (requestDTO.getIdSpecies() != null && !requestDTO.getIdSpecies().equals(existingPet.getSpecies().getIdSpecies())) {
                    Species newSpecies = speciesRepository.findById(requestDTO.getIdSpecies())
                            .orElseThrow(() -> new RuntimeException("Nueva especie no encontrada con id: " + requestDTO.getIdSpecies()));
                    existingPet.setSpecies(newSpecies);
                }

                return petRepository.save(existingPet);
            });
    }

    public boolean deletePetById(Integer id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
            return true;
        }
        return false;
    }
}