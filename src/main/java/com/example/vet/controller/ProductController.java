package com.example.vet.controller;

import com.example.vet.dto.ProductRequestDTO;
import com.example.vet.dto.ProductResponseDTO;
import com.example.vet.model.Product;
import com.example.vet.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "API for managing Products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Create a new product")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {
        Product newProduct = productService.saveProduct(requestDTO);
        Optional<ProductResponseDTO> responseDTO = productService.findProductById(newProduct.getIdProduct());
        return responseDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
                          .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @Operation(summary = "Get a product by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Integer id) {
        return productService.findProductById(id)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Search for products by name")
    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByName(@PathVariable String name) {
        return new ResponseEntity<>(productService.findByProductName(name), HttpStatus.OK);
    }

    @Operation(summary = "Search for products by supplier")
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsBySupplier(@PathVariable Integer supplierId) {
        return new ResponseEntity<>(productService.findBySupplierId(supplierId), HttpStatus.OK);
    }

    @Operation(summary = "Update an existing product")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Integer id,
                                                             @Valid @RequestBody ProductRequestDTO requestDTO) {
        return productService.updateProduct(id, requestDTO)
                .flatMap(updated -> productService.findProductById(updated.getIdProduct()))
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Remove a product by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProductById(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
