package com.example.vet.controller;

import com.example.vet.dto.InvoiceRequestDTO;
import com.example.vet.dto.InvoiceResponseDTO;
import com.example.vet.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/invoices")
@Tag(name = "Invoices", description = "API for managing invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Operation(summary = "Create a new invoice")
    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@Valid @RequestBody InvoiceRequestDTO requestDTO) {
        var newInvoice = invoiceService.saveInvoice(requestDTO);
        var responseDTO = invoiceService.findInvoiceById(newInvoice.getIdInvoice()).orElse(null);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all invoices")
    @GetMapping
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoices() {
        return new ResponseEntity<>(invoiceService.findAllInvoices(), HttpStatus.OK);
    }

    @Operation(summary = "Get an invoice using your ID")
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoiceById(@PathVariable Integer id) {
        Optional<InvoiceResponseDTO> invoice = invoiceService.findInvoiceById(id);
        return invoice.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get an invoice using your ID")
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<InvoiceResponseDTO>> getInvoicesByClientId(@PathVariable Integer clientId) {
        return new ResponseEntity<>(invoiceService.findInvoicesByClientId(clientId), HttpStatus.OK);
    }

    @Operation(summary = "Search invoices by date")
    @GetMapping("/date/{date}")
    public ResponseEntity<List<InvoiceResponseDTO>> getInvoicesByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return new ResponseEntity<>(invoiceService.findInvoicesByDate(date), HttpStatus.OK);
    }

    @Operation(summary = "Update an existing invoice")
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> updateInvoice(@PathVariable Integer id,
                                                         @Valid @RequestBody InvoiceRequestDTO requestDTO) {
    return invoiceService.updateInvoice(id, requestDTO)
            .flatMap(updated -> invoiceService.findInvoiceById(updated.getIdInvoice()))
            .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
}

    @Operation(summary = "Delete an invoice by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Integer id) {
        return invoiceService.deleteInvoiceById(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
