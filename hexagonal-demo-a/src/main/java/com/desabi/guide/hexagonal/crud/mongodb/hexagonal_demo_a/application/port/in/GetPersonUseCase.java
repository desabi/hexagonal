package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in;

import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.model.Person;

/**
 * Inbound port for retrieving a person by their unique identifier.
 * Defines the contract for the get person use case.
 * This interface is implemented by the application service layer.
 */
public interface GetPersonUseCase {

    /**
     * Retrieves a person by their unique identifier.
     *
     * @param id the unique identifier of the person to retrieve
     * @return the person domain object
     * @throws com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.exception.PersonNotFoundException
     *         if no person with the given ID exists
     */
    Person getPersonById(String id);
}