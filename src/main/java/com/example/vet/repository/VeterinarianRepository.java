package com.example.vet.repository;

import com.example.vet.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Integer> {

    Optional<Veterinarian> findByLicenseNumber(String licenseNumber);

    Optional<Veterinarian> findByEmail(String email);

}