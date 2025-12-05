package com.example.vet.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.vet.dto.ProductRequestDTO;
import com.example.vet.dto.ProductResponseDTO;
import com.example.vet.service.ProductService;
import jakarta.validation.Valid;

@Controller
public class ProductGraphql {

    @Autowired
    private ProductService service;

    // -------------------
    // QUERIES
    // -------------------
    @QueryMapping
    public List<ProductResponseDTO> getAllProducts() {
        return service.findAllProducts();
    }

    @QueryMapping
    public ProductResponseDTO getProductById(@Argument Integer id) {
        return service.findProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    @QueryMapping
    public List<ProductResponseDTO> getProductsByName(@Argument String name) {
        return service.findByProductName(name);
    }

    @QueryMapping
    public List<ProductResponseDTO> getProductsBySupplierId(@Argument Integer supplierId) {
        return service.findBySupplierId(supplierId);
    }

    // -------------------
    // MUTATIONS
    // -------------------
    @MutationMapping
    public ProductResponseDTO addProduct(@Valid @Argument ProductRequestDTO productDTO) {
        return service.toDTO(service.saveProduct(productDTO));
    }

    @MutationMapping
    public ProductResponseDTO updateProduct(@Argument Integer id, @Valid @Argument ProductRequestDTO productDTO) {
        return service.updateProduct(id, productDTO)
                .map(service::toDTO)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Integer id) {
        return service.deleteProductById(id);
    }
}