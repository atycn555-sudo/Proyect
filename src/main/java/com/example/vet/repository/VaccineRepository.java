package com.example.vet.repository;

import com.example.vet.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {

    List<Vaccine> findByPetIdPet(Integer petId);

    List<Vaccine> findByVaccineNameContainingIgnoreCase(String vaccineName);

}