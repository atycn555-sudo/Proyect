package com.example.vet.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.vet.dto.InvoiceRequestDTO;
import com.example.vet.dto.InvoiceResponseDTO;
import com.example.vet.service.InvoiceService;
import jakarta.validation.Valid;

@Controller
public class InvoiceGraphql {

    @Autowired
    private InvoiceService service;

    // -------------------
    // QUERIES
    // -------------------
    @QueryMapping
    public List<InvoiceResponseDTO> getAllInvoices() {
        return service.findAllInvoices();
    }

    @QueryMapping
    public InvoiceResponseDTO getInvoiceById(@Argument Integer id) {
        return service.findInvoiceById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id " + id));
    }

    @QueryMapping
    public List<InvoiceResponseDTO> getInvoicesByClientId(@Argument Integer clientId) {
        return service.findInvoicesByClientId(clientId);
    }

    @QueryMapping
    public List<InvoiceResponseDTO> getInvoicesByDate(@Argument String date) {
        java.time.LocalDateTime parsedDate = java.time.LocalDateTime.parse(date);
        return service.findInvoicesByDate(parsedDate);
    }

    // -------------------
    // MUTATIONS
    // -------------------
    @MutationMapping
    public InvoiceResponseDTO addInvoice(@Valid @Argument InvoiceRequestDTO invoiceDTO) {
        return service.toDTO(service.saveInvoice(invoiceDTO));
    }

    @MutationMapping
    public InvoiceResponseDTO updateInvoice(@Argument Integer id, @Valid @Argument InvoiceRequestDTO invoiceDTO) {
        return service.updateInvoice(id, invoiceDTO)
                .map(service::toDTO)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id " + id));
    }

    @MutationMapping
    public Boolean deleteInvoice(@Argument Integer id) {
        return service.deleteInvoiceById(id);
    }
}
