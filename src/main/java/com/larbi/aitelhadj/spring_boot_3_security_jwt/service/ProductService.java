package com.larbi.aitelhadj.spring_boot_3_security_jwt.service;

import com.larbi.aitelhadj.spring_boot_3_security_jwt.exception.ResourceNotFoundException;
import com.larbi.aitelhadj.spring_boot_3_security_jwt.model.Product;
import com.larbi.aitelhadj.spring_boot_3_security_jwt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing products.
 * Provides methods to retrieve all products, get a product by ID,
 * create a new product, and delete a product by ID.
 * Interacts with the {@link ProductRepository} to perform database operations.
 *
 * @author larbi.aitelhadj
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Retrieves all products from the database.
     *
     * @return a list of all {@link Product} objects
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product
     * @return the {@link Product} if found, otherwise null
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
    }

    /**
     * Creates a new product and saves it to the database.
     *
     * @param product the {@link Product} object to save
     * @return the saved {@link Product} object
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete: product with id " + id + " does not exist");
        }
        productRepository.deleteById(id);
    }

}
