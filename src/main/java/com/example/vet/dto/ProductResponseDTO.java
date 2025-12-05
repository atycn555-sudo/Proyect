package com.example.vet.dto;

import java.math.BigDecimal;

public class ProductResponseDTO {
    private Integer idProduct;
    private String productName;
    private BigDecimal price;
    private Integer stock;
    private SupplierSimpleResponseDTO supplier;

    public Integer getIdProduct() { return idProduct; }
    public void setIdProduct(Integer idProduct) { this.idProduct = idProduct; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public SupplierSimpleResponseDTO getSupplier() { return supplier; }
    public void setSupplier(SupplierSimpleResponseDTO supplier) { this.supplier = supplier; }
}
