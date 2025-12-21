package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.service;

import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in.CreatePersonUseCase;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in.DeletePersonUseCase;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in.GetPersonUseCase;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in.ListPersonsUseCase;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in.UpdatePersonUseCase;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.out.PersonRepositoryPort;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.exception.InvalidPersonDataException;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.exception.PersonNotFoundException;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.model.Person;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * TODO: Service: ¿ClassUseCase, ClassAdapter?
 * Use Case Implementation
 * Application service that implements person-related use cases.
 * The PersonService class implements multiple use case interfaces (inbound ports).
 * Orchestrates domain logic and coordinates with repository ports.
 */
@Service
public class PersonService implements CreatePersonUseCase, GetPersonUseCase, ListPersonsUseCase,
    UpdatePersonUseCase, DeletePersonUseCase {

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

    /**
     * Retrieves a person by their unique identifier.
     *
     * @param id the unique identifier of the person to retrieve
     * @return the person domain object
     * @throws PersonNotFoundException if no person with the given ID exists
     */
    @Override
    public Person getPersonById(String id) {
        return personRepositoryPort.findById(id)
            .orElseThrow(() -> PersonNotFoundException.forId(id));
    }

    /**
     * Retrieves all persons from the system.
     *
     * @return a list of all person domain objects, or empty list if no persons exist
     */
    @Override
    public List<Person> getAllPersons() {
        return personRepositoryPort.findAll();
    }

    /**
     * Updates an existing person in the system.
     * Validates the person data and verifies the person exists before updating.
     *
     * @param id the unique identifier of the person to update
     * @param person the person domain object with updated data
     * @return the updated person domain object
     * @throws PersonNotFoundException if no person with the given ID exists
     * @throws InvalidPersonDataException if the person data is invalid
     */
    @Override
    public Person updatePerson(String id, Person person) {
        // Verify person exists
        if (!personRepositoryPort.existsById(id)) {
            throw PersonNotFoundException.forId(id);
        }

        // Validate business rules
        validatePerson(person);

        // Create updated person with the correct ID
        Person updatedPerson = new Person(id, person.getName(), person.getAge(), person.getHeight());

        // Delegate to repository port
        return personRepositoryPort.save(updatedPerson);
    }
    /**
     * Deletes a person from the system by their unique identifier.
     * Verifies the person exists before attempting deletion.
     *
     * @param id the unique identifier of the person to delete
     * @throws PersonNotFoundException if no person with the given ID exists
     */
    @Override
    public void deletePerson(String id) {
        // Verify person exists before deletion
        if (!personRepositoryPort.existsById(id)) {
            throw PersonNotFoundException.forId(id);
        }

        // Delegate to repository port
        personRepositoryPort.deleteById(id);
    }

}