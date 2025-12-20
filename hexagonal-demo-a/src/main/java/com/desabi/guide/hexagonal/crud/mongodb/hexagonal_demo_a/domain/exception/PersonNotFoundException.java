package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.exception;

/**
 * Exception thrown when a requested person cannot be found in the system.
 * This is a domain-level exception that represents a person not existing.
 */
public class PersonNotFoundException extends RuntimeException {

    /**
     * Constructs a new PersonNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining which person was not found
     */
    public PersonNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new PersonNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message explaining which person was not found
     * @param cause the cause of the exception
     */
    public PersonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new PersonNotFoundException with a default message for a specific ID.
     *
     * @param id the unique identifier of the person that was not found
     * @return a new PersonNotFoundException with a formatted message
     */
    public static PersonNotFoundException forId(String id) {
        return new PersonNotFoundException("Person not found with id: " + id);
    }
}