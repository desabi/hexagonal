package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in;

/**
 * Inbound port for deleting a person.
 * Defines the contract for the delete person use case.
 * This interface is implemented by the application service layer.
 */
public interface DeletePersonUseCase {

    /**
     * Deletes a person from the system by their unique identifier.
     *
     * @param id the unique identifier of the person to delete
     * @throws com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.exception.PersonNotFoundException
     *         if no person with the given ID exists
     */
    void deletePerson(String id);
}