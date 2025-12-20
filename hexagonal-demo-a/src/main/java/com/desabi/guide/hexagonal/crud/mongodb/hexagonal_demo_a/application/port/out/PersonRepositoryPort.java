package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.out;


import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.model.Person;
import java.util.List;
import java.util.Optional;

/**
 * TODO: ¿ClassOutputPort?
 * Outbound port for person persistence operations.
 * Defines the contract that persistence adapters must implement.
 * This interface isolates the application core from persistence implementation details.
 */
public interface PersonRepositoryPort {

    /**
     * Saves a person to the repository.
     * If the person has an ID, it updates the existing person.
     * If the person has no ID, it creates a new person with a generated ID.
     *
     * @param person the person to save
     * @return the saved person with ID
     */
    Person save(Person person);

    /**
     * Finds a person by their unique identifier.
     *
     * @param id the unique identifier of the person
     * @return an Optional containing the person if found, or empty if not found
     */
    Optional<Person> findById(String id);

    /**
     * Retrieves all persons from the repository.
     *
     * @return a list of all persons, or empty list if none exist
     */
    List<Person> findAll();

    /**
     * Deletes a person by their unique identifier.
     *
     * @param id the unique identifier of the person to delete
     * @return true if the person was deleted, false if the person was not found
     */
    boolean deleteById(String id);

    /**
     * Checks if a person exists by their unique identifier.
     *
     * @param id the unique identifier of the person
     * @return true if the person exists, false otherwise
     */
    boolean existsById(String id);
}