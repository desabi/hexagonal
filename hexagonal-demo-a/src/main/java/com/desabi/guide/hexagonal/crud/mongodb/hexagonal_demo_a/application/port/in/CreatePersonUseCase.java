package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in;

import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.model.Person;

/**
 * TODO: ¿ClassInputPort?
 * Inteface/Por IN (ClassInputPort)
 * Inbound port for creating a new person.
 * Defines the contract for the create person use case.
 * This interface is implemented by the application service layer.
 */
public interface CreatePersonUseCase {

    /**
     * Creates a new person in the system.
     *
     * @param person the person domain object to create (without ID)
     * @return the created person with generated ID
     * @throws com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.exception.InvalidPersonDataException
     *         if the person data is invalid according to business rules
     */
    Person createPerson(Person person);
}