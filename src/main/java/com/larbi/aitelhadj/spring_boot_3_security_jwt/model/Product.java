package com.larbi.aitelhadj.spring_boot_3_security_jwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a product.
 * Contains basic product information such as name, price, and description.
 * Mapped to a database table using JPA annotations.
 *
 * @author larbi.aitelhadj
 */
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String description;

}
