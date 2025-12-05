package com.example.vet.service;

import com.example.vet.dto.ProductRequestDTO;
import com.example.vet.dto.ProductResponseDTO;
import com.example.vet.dto.SupplierSimpleResponseDTO;
import com.example.vet.model.Product;
import com.example.vet.model.Supplier;
import com.example.vet.repository.ProductRepository;
import com.example.vet.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional
    public Product saveProduct(ProductRequestDTO dto) {
        Supplier supplier = supplierRepository.findById(dto.getIdSupplier())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + dto.getIdSupplier()));

        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setSupplier(supplier);

        return productRepository.save(product);
    }

    public List<ProductResponseDTO> findAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponseDTO> findProductById(Integer id) {
        return productRepository.findById(id)
                .map(this::toDTO);
    }

    public List<ProductResponseDTO> findByProductName(String name) {
        return productRepository.findByProductNameIgnoreCase(name).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDTO> findBySupplierId(Integer supplierId) {
        return productRepository.findBySupplierIdSupplier(supplierId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<Product> updateProduct(Integer id, ProductRequestDTO dto) {
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setProductName(dto.getProductName());
                    existing.setPrice(dto.getPrice());
                    existing.setStock(dto.getStock());

                    if (!existing.getSupplier().getIdSupplier().equals(dto.getIdSupplier())) {
                        Supplier supplier = supplierRepository.findById(dto.getIdSupplier())
                                .orElseThrow(() -> new RuntimeException(
                                        "Proveedor no encontrado con id: " + dto.getIdSupplier()));
                        existing.setSupplier(supplier);
                    }

                    return productRepository.save(existing);
                });
    }

    public boolean deleteProductById(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Conversión a DTO
    public ProductResponseDTO toDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setIdProduct(product.getIdProduct());
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());

        SupplierSimpleResponseDTO supplierDTO = new SupplierSimpleResponseDTO();
        supplierDTO.setIdSupplier(product.getSupplier().getIdSupplier());
        supplierDTO.setName(product.getSupplier().getName());
        dto.setSupplier(supplierDTO);

        return dto;
    }
}
