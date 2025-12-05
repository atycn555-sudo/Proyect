package com.example.vet.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_invoice")
    private Integer idInvoice;

    @Column(name = "invoice_date")
    private LocalDateTime invoiceDate;

    @Column(name = "service_amount")
    private BigDecimal serviceAmount;

    @Column(name = "product_amount")
    private BigDecimal productAmount;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "payment_status")
    private String paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_history")
    private MedicalHistory medicalHistory;
    

    public Integer getIdInvoice() { return idInvoice; }
    public void setIdInvoice(Integer idInvoice) { this.idInvoice = idInvoice; }
    public LocalDateTime getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDateTime invoiceDate) { this.invoiceDate = invoiceDate; }
    public BigDecimal getServiceAmount() { return serviceAmount; }
    public void setServiceAmount(BigDecimal serviceAmount) { this.serviceAmount = serviceAmount; }
    public BigDecimal getProductAmount() { return productAmount; }
    public void setProductAmount(BigDecimal productAmount) { this.productAmount = productAmount; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public MedicalHistory getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(MedicalHistory medicalHistory) { this.medicalHistory = medicalHistory; }
}