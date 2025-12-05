package com.example.vet.repository;

import com.example.vet.model.ClinicService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicServiceRepository extends JpaRepository<ClinicService, Integer> {


    List<ClinicService> findByServiceNameContainingIgnoreCase(String serviceName);

}