package com.example.vet.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InvoiceRequestDTO {
    private LocalDateTime invoiceDate;
    private BigDecimal serviceAmount;
    private BigDecimal productAmount;
    private BigDecimal total;
    private String paymentStatus;
    private Integer idClient;
    private Integer idMedicalHistory;

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

    public Integer getIdClient() { return idClient; }
    public void setIdClient(Integer idClient) { this.idClient = idClient; }

    public Integer getIdMedicalHistory() { return idMedicalHistory; }
    public void setIdMedicalHistory(Integer idMedicalHistory) { this.idMedicalHistory = idMedicalHistory; }
}
