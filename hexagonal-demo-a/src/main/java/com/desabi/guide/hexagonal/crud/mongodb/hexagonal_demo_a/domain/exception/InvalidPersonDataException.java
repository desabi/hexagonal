package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.exception;

/**
 * Exception thrown when person data violates business rules or constraints.
 * This is a domain-level exception that represents invalid data according to business logic.
 */
public class InvalidPersonDataException extends RuntimeException {

    /**
     * Constructs a new InvalidPersonDataException with the specified detail message.
     *
     * @param message the detail message explaining why the person data is invalid
     */
    public InvalidPersonDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidPersonDataException with the specified detail message and cause.
     *
     * @param message the detail message explaining why the person data is invalid
     * @param cause the cause of the exception
     */
    public InvalidPersonDataException(String message, Throwable cause) {
        super(message, cause);
    }
}