package com.example.vet.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "service") 
public class ClinicService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Integer idService;

    @Column(name = "service_name", unique = true, nullable = false, length = 100)
    private String serviceName;

    @Column(name = "price", nullable = false)
    private BigDecimal price;



    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

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