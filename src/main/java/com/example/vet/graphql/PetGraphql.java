package com.example.vet.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.vet.dto.PetRequestDTO;
import com.example.vet.dto.PetResponseDTO;
import com.example.vet.model.Pet;
import com.example.vet.service.PetService;
import jakarta.validation.Valid;

@Controller
public class PetGraphql {

    @Autowired
    private PetService service;

    @QueryMapping
    public List<PetResponseDTO> getAllPets() {
        return service.findAllPets().stream()
                .map(this::toDTO)
                .toList();
    }

    @QueryMapping
    public PetResponseDTO getPetById(@Argument Integer id) {
        return service.findPetById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Pet not found with id " + id));
    }

    @QueryMapping
    public List<PetResponseDTO> getPetsByClientId(@Argument Integer clientId) {
        return service.findPetsByClientId(clientId).stream()
                .map(this::toDTO)
                .toList();
    }

    @MutationMapping
    public PetResponseDTO addPet(@Valid @Argument PetRequestDTO petDTO) {
        Pet saved = service.savePet(petDTO);
        return toDTO(saved);
    }

    @MutationMapping
    public PetResponseDTO updatePet(@Argument Integer id, @Valid @Argument PetRequestDTO petDTO) {
        return service.updatePet(id, petDTO)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Pet not found with id " + id));
    }

    @MutationMapping
    public Boolean deletePet(@Argument Integer id) {
        return service.deletePetById(id);
    }

    // -------------------
    // Conversión a DTO
    // -------------------
    private PetResponseDTO toDTO(Pet pet) {
        PetResponseDTO dto = new PetResponseDTO();
        dto.setIdPet(pet.getIdPet());
        dto.setName(pet.getName());
        dto.setBirthDate(pet.getBirthDate());
        dto.setBreed(pet.getBreed());

        // ClientSimpleResponseDTO
        com.example.vet.dto.ClientSimpleResponseDTO clientDTO = new com.example.vet.dto.ClientSimpleResponseDTO();
        clientDTO.setIdClient(pet.getClient().getIdClient());
        clientDTO.setFirstName(pet.getClient().getFirstName());
        clientDTO.setLastName(pet.getClient().getLastName());
        dto.setClient(clientDTO);

        // SpeciesResponseDTO
        com.example.vet.dto.SpeciesResponseDTO speciesDTO = new com.example.vet.dto.SpeciesResponseDTO();
        speciesDTO.setIdSpecies(pet.getSpecies().getIdSpecies());
        speciesDTO.setSpeciesName(pet.getSpecies().getSpeciesName());
        dto.setSpecies(speciesDTO);

        return dto;
    }
}
