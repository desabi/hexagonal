package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.out.persistence.mapper;

import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.model.Person;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.out.persistence.entity.PersonEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Person domain model and PersonEntity.
 * Handles the translation between the domain layer and the persistence layer.
 */
@Component
public class PersonEntityMapper {

    /**
     * Default constructor.
     */
    public PersonEntityMapper() {
    }

    /**
     * Converts a Person domain model to a PersonEntity.
     * Used when persisting domain objects to MongoDB.
     *
     * @param person the domain object
     * @return a PersonEntity ready for persistence
     */
    public PersonEntity toEntity(Person person) {
        if (person == null) {
            return null;
        }

        return new PersonEntity(
            person.getId(),
            person.getName(),
            person.getAge(),
            person.getHeight()
        );
    }

    /**
     * Converts a PersonEntity to a Person domain model.
     * Used when retrieving data from MongoDB.
     *
     * @param entity the persistence entity
     * @return a Person domain object
     */
    public Person toDomain(PersonEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Person(
            entity.getId(),
            entity.getName(),
            entity.getAge(),
            entity.getHeight()
        );
    }

    // Getters and setters (if needed)

    // equals() and hashCode()

    // toString()
}