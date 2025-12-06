package com.example.vet.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // <-- 1. IMPORTA LA ANOTACIÓN
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.vet.dto.ClientRequestDTO;
import com.example.vet.dto.ClientResponseDTO;
import com.example.vet.model.Client;
import com.example.vet.service.ClientService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clients")
@Tag(name = "Clients", description = "API for managing Clients")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO requestDTO) { // <-- 2. AÑADE @Valid
        Client client = modelMapper.map(requestDTO, Client.class);
        Client newClient = clientService.saveClient(client);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newClient.getIdClient())
                .toUri();
        
        return ResponseEntity.created(location).body(modelMapper.map(newClient, ClientResponseDTO.class));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Integer id, @Valid @RequestBody ClientRequestDTO requestDTO) { // <-- 2. AÑADE @Valid
        Client clientDetails = modelMapper.map(requestDTO, Client.class);
        return clientService.updateClient(id, clientDetails)
                .map(updatedClient -> ResponseEntity.ok(modelMapper.map(updatedClient, ClientResponseDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        List<Client> clients = clientService.findAllClients();
        List<ClientResponseDTO> dtos = clients.stream()
                .map(client -> modelMapper.map(client, ClientResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Integer id) {
        return clientService.findClientById(id)
                .map(client -> ResponseEntity.ok(modelMapper.map(client, ClientResponseDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        return clientService.deleteClientById(id) 
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
