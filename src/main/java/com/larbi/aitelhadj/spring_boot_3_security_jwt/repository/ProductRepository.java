package com.larbi.aitelhadj.spring_boot_3_security_jwt.repository;

import com.larbi.aitelhadj.spring_boot_3_security_jwt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for {@link Product} entities.
 * Provides CRUD operations and basic data access methods
 * by extending {@link JpaRepository}.
 *
 * @author larbi.aitelhadj
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
