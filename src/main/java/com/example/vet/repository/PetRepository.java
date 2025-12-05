package com.example.vet.repository;

import com.example.vet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    List<Pet> findByNameContainingIgnoreCase(String name);
    
    List<Pet> findByClientIdClient(Integer clientId);
}