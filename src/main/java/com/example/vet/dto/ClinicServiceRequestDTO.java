package com.example.vet.dto;

import java.math.BigDecimal;

public class ClinicServiceRequestDTO {
    private String serviceName;
    private BigDecimal price;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
