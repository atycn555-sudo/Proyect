package com.example.vet.repository;

import com.example.vet.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    Optional<Client> findByEmail(String email);


    List<Client> findByFirstNameContainingIgnoreCase(String firstName);
}