package com.example.vet.service;

import com.example.vet.model.Client;
import com.example.vet.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> findClientById(Integer id) {
        return clientRepository.findById(id);
    }
    
    public Optional<Client> updateClient(Integer id, Client clientDetails) {
        return clientRepository.findById(id)
                .map(existingClient -> {
                    existingClient.setFirstName(clientDetails.getFirstName());
                    existingClient.setLastName(clientDetails.getLastName());
                    existingClient.setPhone(clientDetails.getPhone());
                    existingClient.setEmail(clientDetails.getEmail());
                    
                    if (clientDetails.getAddress() != null) {
                        existingClient.getAddress().setStreet(clientDetails.getAddress().getStreet());
                        existingClient.getAddress().setExternalNumber(clientDetails.getAddress().getExternalNumber());
                        existingClient.getAddress().setNeighborhood(clientDetails.getAddress().getNeighborhood());
                        existingClient.getAddress().setCity(clientDetails.getAddress().getCity());
                        existingClient.getAddress().setZipCode(clientDetails.getAddress().getZipCode());
                    }
                    
                    return clientRepository.save(existingClient);
                });
    }

    public boolean deleteClientById(Integer id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // public List<Client> findClientsByName(String firstName) {
    //     return clientRepository.findByFirstNameContainingIgnoreCase(firstName);
    // }
}