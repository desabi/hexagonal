package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.service;

import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in.CreatePersonUseCase;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.out.PersonRepositoryPort;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.exception.InvalidPersonDataException;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.model.Person;
import org.springframework.stereotype.Service;

/**
 * TODO: Service: ¿ClassUseCase, ClassAdapter?
 * Use Case Implementation
 * Application service that implements person-related use cases.
 * Orchestrates domain logic and coordinates with repository ports.
 */
@Service
public class PersonService implements CreatePersonUseCase {

    private final PersonRepositoryPort personRepositoryPort;

    /**
     * Constructor for PersonService.
     *
     * @param personRepositoryPort the repository port for person persistence operations
     */
    public PersonService(PersonRepositoryPort personRepositoryPort) {
        this.personRepositoryPort = personRepositoryPort;
    }

    /**
     * Creates a new person in the system.
     * Validates the person data before persisting.
     *
     * @param person the person domain object to create
     * @return the created person with generated ID
     * @throws InvalidPersonDataException if the person data is invalid
     */
    @Override
    public Person createPerson(Person person) {
        // Validate business rules
        validatePerson(person);
        
        // Delegate to repository port
        return personRepositoryPort.save(person);
    }

    /**
     * Validates person data according to business rules.
     *
     * @param person the person to validate
     * @throws InvalidPersonDataException if validation fails
     */
    private void validatePerson(Person person) {
        if (person == null) {
            throw new InvalidPersonDataException("Person cannot be null");
        }
        
        if (person.getName() == null || person.getName().trim().isEmpty()) {
            throw new InvalidPersonDataException("Person name cannot be empty");
        }
        
        if (person.getAge() == null || person.getAge() < 0) {
            throw new InvalidPersonDataException("Person age must be a positive number");
        }
        
        if (person.getHeight() == null || person.getHeight() <= 0) {
            throw new InvalidPersonDataException("Person height must be a positive number");
        }
    }
}