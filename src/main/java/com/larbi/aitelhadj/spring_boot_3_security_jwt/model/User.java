package com.larbi.aitelhadj.spring_boot_3_security_jwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a system user.
 * Contains the user's login information and role.
 * Mapped to the "User" table in the database.
 *
 * @author larbi.aitelhadj
 */
@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;

}
