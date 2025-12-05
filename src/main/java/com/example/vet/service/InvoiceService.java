package com.example.vet.service;

import com.example.vet.dto.InvoiceRequestDTO;
import com.example.vet.dto.InvoiceResponseDTO;
import com.example.vet.dto.ClientSimpleResponseDTO;
import com.example.vet.dto.MedicalHistorySimpleResponseDTO;
import com.example.vet.model.Client;
import com.example.vet.model.Invoice;
import com.example.vet.model.MedicalHistory;
import com.example.vet.repository.ClientRepository;
import com.example.vet.repository.InvoiceRepository;
import com.example.vet.repository.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    // -------------------
    // CREATE
    // -------------------
    @Transactional
    public Invoice saveInvoice(InvoiceRequestDTO dto) {
        Client client = clientRepository.findById(dto.getIdClient())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + dto.getIdClient()));

        MedicalHistory history = medicalHistoryRepository.findById(dto.getIdMedicalHistory())
                .orElseThrow(
                        () -> new RuntimeException("Historial no encontrado con id: " + dto.getIdMedicalHistory()));

        Invoice invoice = new Invoice();
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setServiceAmount(dto.getServiceAmount());
        invoice.setProductAmount(dto.getProductAmount());
        invoice.setTotal(dto.getTotal());
        invoice.setPaymentStatus(dto.getPaymentStatus());
        invoice.setClient(client);
        invoice.setMedicalHistory(history);

        return invoiceRepository.save(invoice);
    }

    // -------------------
    // READ
    // -------------------
    public List<InvoiceResponseDTO> findAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<InvoiceResponseDTO> findInvoiceById(Integer id) {
        return invoiceRepository.findById(id)
                .map(this::toDTO);
    }

    public List<InvoiceResponseDTO> findInvoicesByClientId(Integer clientId) {
        return invoiceRepository.findAll().stream()
                .filter(i -> i.getClient().getIdClient().equals(clientId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<InvoiceResponseDTO> findInvoicesByDate(LocalDateTime date) {
        return invoiceRepository.findAll().stream()
                .filter(i -> i.getInvoiceDate().toLocalDate().equals(date.toLocalDate()))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // -------------------
    // UPDATE
    // -------------------
    @Transactional
    public Optional<Invoice> updateInvoice(Integer id, InvoiceRequestDTO dto) {
        return invoiceRepository.findById(id)
                .map(existing -> {
                    existing.setInvoiceDate(dto.getInvoiceDate());
                    existing.setServiceAmount(dto.getServiceAmount());
                    existing.setProductAmount(dto.getProductAmount());
                    existing.setTotal(dto.getTotal());
                    existing.setPaymentStatus(dto.getPaymentStatus());

                    if (!existing.getClient().getIdClient().equals(dto.getIdClient())) {
                        Client client = clientRepository.findById(dto.getIdClient())
                                .orElseThrow(() -> new RuntimeException(
                                        "Cliente no encontrado con id: " + dto.getIdClient()));
                        existing.setClient(client);
                    }

                    if (!existing.getMedicalHistory().getIdHistory().equals(dto.getIdMedicalHistory())) {
                        MedicalHistory history = medicalHistoryRepository.findById(dto.getIdMedicalHistory())
                                .orElseThrow(() -> new RuntimeException(
                                        "Historial no encontrado con id: " + dto.getIdMedicalHistory()));
                        existing.setMedicalHistory(history);
                    }

                    return invoiceRepository.save(existing);
                });
    }

    // -------------------
    // DELETE
    // -------------------
    public boolean deleteInvoiceById(Integer id) {
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // -------------------
    // CONVERSIÓN A DTO
    // -------------------
    public InvoiceResponseDTO toDTO(Invoice invoice) {
        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        dto.setIdInvoice(invoice.getIdInvoice());
        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setServiceAmount(invoice.getServiceAmount());
        dto.setProductAmount(invoice.getProductAmount());
        dto.setTotal(invoice.getTotal());
        dto.setPaymentStatus(invoice.getPaymentStatus());

        ClientSimpleResponseDTO clientDTO = new ClientSimpleResponseDTO();
        clientDTO.setIdClient(invoice.getClient().getIdClient());
        clientDTO.setFirstName(invoice.getClient().getFirstName());
        clientDTO.setLastName(invoice.getClient().getLastName());
        dto.setClient(clientDTO);

        MedicalHistorySimpleResponseDTO historyDTO = new MedicalHistorySimpleResponseDTO();
        historyDTO.setIdHistory(invoice.getMedicalHistory().getIdHistory());
        historyDTO.setDate(invoice.getMedicalHistory().getDate());
        historyDTO.setDiagnosis(invoice.getMedicalHistory().getDiagnosis());
        dto.setMedicalHistory(historyDTO);

        return dto;
    }
}
