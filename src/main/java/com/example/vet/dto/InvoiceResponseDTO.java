package com.example.vet.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InvoiceResponseDTO {
    private Integer idInvoice;
    private LocalDateTime invoiceDate;
    private BigDecimal serviceAmount;
    private BigDecimal productAmount;
    private BigDecimal total;
    private String paymentStatus;
    private ClientSimpleResponseDTO client;
    private MedicalHistorySimpleResponseDTO medicalHistory;

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

    public ClientSimpleResponseDTO getClient() { return client; }
    public void setClient(ClientSimpleResponseDTO client) { this.client = client; }

    public MedicalHistorySimpleResponseDTO getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(MedicalHistorySimpleResponseDTO medicalHistory) { this.medicalHistory = medicalHistory; }
}
