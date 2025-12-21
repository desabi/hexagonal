package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in;


import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.model.Person;

/**
 * Inbound port for updating an existing person.
 * Defines the contract for the update person use case.
 * This interface is implemented by the application service layer.
 */
public interface UpdatePersonUseCase {

    /**
     * Updates an existing person in the system.
     *
     * @param id the unique identifier of the person to update
     * @param person the person domain object with updated data (without ID)
     * @return the updated person domain object
     * @throws com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.exception.PersonNotFoundException
     *         if no person with the given ID exists
     * @throws com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.exception.InvalidPersonDataException
     *         if the person data is invalid according to business rules
     */
    Person updatePerson(String id, Person person);
}