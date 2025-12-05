package com.example.vet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

import com.example.vet.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
    List<Invoice> findByInvoiceDate(LocalDateTime invoiceDate);
}
