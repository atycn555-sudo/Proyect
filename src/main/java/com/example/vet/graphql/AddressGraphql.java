package com.example.vet.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.vet.model.Address;
import com.example.vet.service.AddressService;
import jakarta.validation.Valid;

@Controller
public class AddressGraphql {

    @Autowired
    private AddressService service;

    // -------------------
    // QUERIES
    // -------------------
    @QueryMapping
    public List<Address> getAllAddresses() {
        return service.findAllAddresses();
    }

    @QueryMapping
    public Address getAddressById(@Argument Integer id) {
        return service.findAddressById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id " + id));
    }

    // -------------------
    // MUTATIONS
    // -------------------
    @MutationMapping
    public Address addAddress(@Valid @Argument Address address) {
        return service.saveAddress(address);
    }
}
