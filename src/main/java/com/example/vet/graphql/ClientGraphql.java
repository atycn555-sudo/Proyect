package com.example.vet.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.vet.model.Client;
import com.example.vet.service.ClientService;
import jakarta.validation.Valid;

@Controller
public class ClientGraphql {

    @Autowired
    private ClientService service;

    // -------------------
    // QUERIES
    // -------------------
    @QueryMapping
    public List<Client> getAllClients() {
        return service.findAllClients(); // usa tu método real
    }

    @QueryMapping
    public Client getClientById(@Argument Integer id) {
        return service.findClientById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));
    }

    // -------------------
    // MUTATIONS
    // -------------------
    @MutationMapping
    public Client addClient(@Valid @Argument Client client) {
        return service.saveClient(client); // usa tu método real
    }
}
