package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in;

import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.model.Person;
import java.util.List;

/**
 * Inbound port for retrieving all persons.
 * Defines the contract for the list persons use case.
 * This interface is implemented by the application service layer.
 */
public interface ListPersonsUseCase {

    /**
     * Retrieves all persons from the system.
     *
     * @return a list of all person domain objects, or empty list if no persons exist
     */
    List<Person> getAllPersons();
}