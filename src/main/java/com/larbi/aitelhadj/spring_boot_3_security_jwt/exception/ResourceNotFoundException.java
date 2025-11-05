package com.larbi.aitelhadj.spring_boot_3_security_jwt.exception;

/**
 * Exception thrown when a requested resource is not found.
 *
 * @author larbi.aitelhadj
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Creates a new {@code ResourceNotFoundException} with the specified detail message.
     *
     * @param message the detail message explaining the error
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

