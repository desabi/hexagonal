package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.out.persistence.repository;

import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.out.persistence.entity.PersonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for PersonEntity.
 * Provides CRUD operations and query methods for person persistence.
 * Extends MongoRepository to leverage Spring Data's built-in functionality.
 */
@Repository
public interface PersonMongoRepository extends MongoRepository<PersonEntity, String> {

    // Spring Data MongoDB provides default implementations for:
    // - save(PersonEntity entity)
    // - findById(String id)
    // - findAll()
    // - deleteById(String id)
    // - existsById(String id)
    // - count()
    // and many more...

    // Custom query methods can be added here if needed, for example:
    // List<PersonEntity> findByName(String name);
    // List<PersonEntity> findByAgeBetween(Integer minAge, Integer maxAge);
}