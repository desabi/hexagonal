package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.out.persistence;


import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.out.PersonRepositoryPort;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.model.Person;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.out.persistence.entity.PersonEntity;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.out.persistence.mapper.PersonEntityMapper;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.out.persistence.repository.PersonMongoRepository;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TODO: Service: ¿ClassAdapter or ClassUseCase?
 * Adapter that implements the PersonRepositoryPort for MongoDB persistence. Acts as a bridge
 * between the application core and MongoDB infrastructure. Translates domain operations into
 * MongoDB-specific operations.
 */
@Component
public class PersonPersistenceAdapter implements PersonRepositoryPort {

  private final PersonMongoRepository personMongoRepository;
  private final PersonEntityMapper personEntityMapper;

  /**
   * Constructor for PersonPersistenceAdapter.
   *
   * @param personMongoRepository the Spring Data MongoDB repository
   * @param personEntityMapper    the mapper for converting between domain and entity
   */
  public PersonPersistenceAdapter(
      PersonMongoRepository personMongoRepository, PersonEntityMapper personEntityMapper) {
    this.personMongoRepository = personMongoRepository;
    this.personEntityMapper = personEntityMapper;
  }

  /**
   * Saves a person to MongoDB.
   *
   * @param person the person domain object to save
   * @return the saved person with generated ID
   */
  @Override
  public Person save(Person person) {
    PersonEntity entity = personEntityMapper.toEntity(person);
    PersonEntity savedEntity = personMongoRepository.save(entity);
    return personEntityMapper.toDomain(savedEntity);
  }

  /**
   * Finds a person by their unique identifier.
   *
   * @param id the unique identifier of the person
   * @return an Optional containing the person if found, or empty if not found
   */
  @Override
  public Optional<Person> findById(String id) {
    return personMongoRepository.findById(id)
        .map(personEntityMapper::toDomain);
  }

  /**
   * Retrieves all persons from MongoDB.
   *
   * @return a list of all persons
   */
  @Override
  public List<Person> findAll() {
    return personMongoRepository.findAll()
        .stream()
        .map(personEntityMapper::toDomain)
        .collect(Collectors.toList());
  }

  /**
   * Deletes a person by their unique identifier.
   *
   * @param id the unique identifier of the person to delete
   * @return true if the person was deleted, false if the person was not found
   */
  @Override
  public boolean deleteById(String id) {
    if (personMongoRepository.existsById(id)) {
      personMongoRepository.deleteById(id);
      return true;
    }
    return false;
  }

  /**
   * Checks if a person exists by their unique identifier.
   *
   * @param id the unique identifier of the person
   * @return true if the person exists, false otherwise
   */
  @Override
  public boolean existsById(String id) {
    return personMongoRepository.existsById(id);
  }
}