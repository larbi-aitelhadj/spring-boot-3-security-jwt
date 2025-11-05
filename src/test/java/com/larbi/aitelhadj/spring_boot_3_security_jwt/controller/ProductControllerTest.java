package com.larbi.aitelhadj.spring_boot_3_security_jwt.controller;

import com.larbi.aitelhadj.spring_boot_3_security_jwt.model.Product;
import com.larbi.aitelhadj.spring_boot_3_security_jwt.service.CustomerUserDetailsService;
import com.larbi.aitelhadj.spring_boot_3_security_jwt.service.JwtService;
import com.larbi.aitelhadj.spring_boot_3_security_jwt.service.ProductService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.List;

/**
 * Unit tests for {@link ProductController} using {@link MockMvc}.
 * Tests main REST endpoints of the controller, including:
 *    - getAllProducts() – verifies all products are returned correctly.
 *    - getProductById() – verifies a product can be retrieved by ID.
 *    - createProduct() – verifies a product can be created.
 *    - deleteProductById() – verifies a product can be deleted.
 * Mocks {@link ProductService}, {@link JwtService}, and {@link CustomerUserDetailsService}
 * to isolate controller logic from service and security layers.
 *
 * @author larbi.aitelhadj
 */
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(ProductControllerTest.MockConfig.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private JwtService jwtFilter;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public ProductService productService() {
            return Mockito.mock(ProductService.class);
        }

        @Bean
        public JwtService jwtService() {
            return Mockito.mock(JwtService.class);
        }

        @Bean
        public CustomerUserDetailsService customerUserDetailsService() {
            return Mockito.mock(CustomerUserDetailsService.class);
        }
    }

    @Test
    void getAllProducts() throws Exception {
        Product product1 = new Product(1L, "product1", 456.90, "description1");
        Product product2 = new Product(2L, "product2", 978.23, "description2");
        when(productService.getAllProducts()).thenReturn(List.of(product1, product2));
        mockMvc.perform(get("/api/products/getAllProducts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("product1"))
                .andExpect(jsonPath("$[0].price").value(456.90))
                .andExpect(jsonPath("$[0].description").value("description1"));
    }

    @Test
    void getProductById() throws Exception {
        Product product = new Product(1L, "product1", 8976.23, "description1");
        when(productService.getProductById(1L)).thenReturn(product);
        mockMvc.perform(get("/api/products/getProductById").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("product1"))
                .andExpect(jsonPath("$.price").value(8976.23))
                .andExpect(jsonPath("$.description").value("description1"));
    }

    @Test
    void createProduct() throws Exception {
        String productJson = """
                    {
                        "id": 1,
                        "name": "product1",
                        "price": 987.45,
                        "description": "description1"
                    }
                """;
        Product product = new Product(1L, "product1", 987.45, "description1");
        when(productService.createProduct(product)).thenReturn(product);
        mockMvc.perform(post("/api/products/createProduct").contentType(MediaType.APPLICATION_JSON).content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("product1"))
                .andExpect(jsonPath("$.price").value(987.45))
                .andExpect(jsonPath("$.description").value("description1"));
    }

    @Test
    void deleteProductById() throws Exception {
        mockMvc.perform(delete("/api/products/deleteProductById/1"))
                .andExpect(status().isOk());
    }
}