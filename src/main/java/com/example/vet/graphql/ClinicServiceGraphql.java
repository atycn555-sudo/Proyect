package com.example.vet.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.vet.model.ClinicService;
import com.example.vet.service.ClinicServiceService;
import jakarta.validation.Valid;

@Controller
public class ClinicServiceGraphql {

    @Autowired
    private ClinicServiceService service;

    // -------------------
    // QUERIES
    // -------------------
    @QueryMapping
    public List<ClinicService> getAllClinicServices() {
        return service.findAllClinicServices();
    }

    @QueryMapping
    public ClinicService getClinicServiceById(@Argument Integer id) {
        return service.findClinicServiceById(id)
                .orElseThrow(() -> new RuntimeException("ClinicService not found with id " + id));
    }

    // -------------------
    // MUTATIONS
    // -------------------
    @MutationMapping
    public ClinicService addClinicService(@Valid @Argument ClinicService clinicService) {
        return service.saveClinicService(clinicService);
    }
}
