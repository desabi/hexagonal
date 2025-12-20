package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.input.rest.mapper;


import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.model.Person;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.input.rest.dto.PersonRequestDto;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.input.rest.dto.PersonResponseDto;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Person domain model and DTOs.
 * Handles the translation between the domain layer and the REST API layer.
 */
@Component
public class PersonDtoMapper {

    /**
     * Default constructor.
     */
    public PersonDtoMapper() {
    }

    /**
     * Converts a PersonRequestDto to a Person domain model.
     * Used when receiving data from API clients.
     *
     * @param dto the request DTO containing person data
     * @return a Person domain object
     */
    public Person toDomain(PersonRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return new Person(
            dto.getName(),
            dto.getAge(),
            dto.getHeight()
        );
    }

    /**
     * Converts a Person domain model to a PersonResponseDto.
     * Used when sending data back to API clients.
     *
     * @param person the domain object
     * @return a response DTO containing person data
     */
    public PersonResponseDto toResponseDto(Person person) {
        if (person == null) {
            return null;
        }

        return new PersonResponseDto(
            person.getId(),
            person.getName(),
            person.getAge(),
            person.getHeight()
        );
    }

    /**
     * Updates an existing Person domain model with data from a PersonRequestDto.
     * Preserves the ID while updating other fields.
     *
     * @param dto the request DTO containing updated person data
     * @param existingPerson the existing person to update
     * @return the updated Person domain object
     */
    public Person updateDomain(PersonRequestDto dto, Person existingPerson) {
        if (dto == null || existingPerson == null) {
            return existingPerson;
        }

        return new Person(
            existingPerson.getId(),
            dto.getName(),
            dto.getAge(),
            dto.getHeight()
        );
    }

    // Getters and setters (if needed)

    // equals() and hashCode()

    // toString()
}