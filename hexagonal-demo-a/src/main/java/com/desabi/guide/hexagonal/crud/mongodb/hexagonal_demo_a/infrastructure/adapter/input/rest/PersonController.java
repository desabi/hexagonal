package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.input.rest;

import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in.CreatePersonUseCase;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in.GetPersonUseCase;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.application.port.in.ListPersonsUseCase;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.model.Person;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.input.rest.dto.PersonRequestDto;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.input.rest.dto.PersonResponseDto;
import com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.input.rest.mapper.PersonDtoMapper;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for person-related operations.
 * Exposes HTTP endpoints for managing persons.
 * Acts as an adapter between HTTP clients and the application core.
 */
@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final CreatePersonUseCase createPersonUseCase;
    private final GetPersonUseCase getPersonUseCase;
    private final ListPersonsUseCase listPersonsUseCase;
    private final PersonDtoMapper personDtoMapper;

    /**
     * Constructor for PersonController.
     *
     * @param createPersonUseCase the use case for creating persons
     * @param personDtoMapper the mapper for converting between DTOs and domain models
     */
    public PersonController(
            CreatePersonUseCase createPersonUseCase, GetPersonUseCase getPersonUseCase,
        ListPersonsUseCase listPersonsUseCase,
            PersonDtoMapper personDtoMapper) {
        this.createPersonUseCase = createPersonUseCase;
      this.getPersonUseCase = getPersonUseCase;
      this.listPersonsUseCase = listPersonsUseCase;
      this.personDtoMapper = personDtoMapper;
    }

    /**
     * Creates a new person.
     * 
     * @param requestDto the person data from the request body
     * @return ResponseEntity with the created person and HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<PersonResponseDto> createPerson(
            @Valid @RequestBody PersonRequestDto requestDto) {
        
        // Map DTO to domain model
        Person person = personDtoMapper.toDomain(requestDto);
        
        // Execute use case
        Person createdPerson = createPersonUseCase.createPerson(person);
        
        // Map domain model to response DTO
        PersonResponseDto responseDto = personDtoMapper.toResponseDto(createdPerson);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    /**
     * Retrieves a person by their unique identifier.
     *
     * @param id the unique identifier of the person
     * @return ResponseEntity with the person data and HTTP 200 status
     */
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDto> getPersonById(@PathVariable String id) {

        // Execute use case
        Person person = getPersonUseCase.getPersonById(id);

        // Map domain model to response DTO
        PersonResponseDto responseDto = personDtoMapper.toResponseDto(person);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    /**
     * Retrieves all persons.
     *
     * @return ResponseEntity with a list of all persons and HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<PersonResponseDto>> getAllPersons() {

        // Execute use case
        List<Person> persons = listPersonsUseCase.getAllPersons();

        // Map domain models to response DTOs
        List<PersonResponseDto> responseDtos = persons.stream()
            .map(personDtoMapper::toResponseDto)
            .toList();

        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }
}