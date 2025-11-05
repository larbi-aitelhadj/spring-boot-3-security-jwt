package com.larbi.aitelhadj.spring_boot_3_security_jwt.controller;

import com.larbi.aitelhadj.spring_boot_3_security_jwt.model.Product;
import com.larbi.aitelhadj.spring_boot_3_security_jwt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing products.
 * Provides endpoints to retrieve, create, and delete products.
 * Uses {@link ProductService} to handle business logic.
 *
 * @author larbi.aitelhadj
 */
@RestController
@RequestMapping(value = "/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Retrieves all products.
     *
     * @return a list of all {@link Product} entities
     */
    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product
     * @return the {@link Product} with the given ID
     */
    @GetMapping(value = "/getProductById")
    public ResponseEntity<Product> getProductById(@RequestParam Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    /**
     * Creates a new product.
     *
     * @param product the {@link Product} to create
     * @return the created {@link Product}
     */
    @PostMapping(value = "/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @return a message indicating success or failure
     */
    @DeleteMapping(value = "/deleteProductById/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok("Product deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
