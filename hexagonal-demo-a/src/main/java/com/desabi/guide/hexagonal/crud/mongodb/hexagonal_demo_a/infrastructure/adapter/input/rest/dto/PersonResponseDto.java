package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.input.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object for Person responses.
 * Used to send person data to REST API clients.
 */
public class PersonResponseDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("height")
    private Double height;

    /**
     * Default constructor (required by Jackson for serialization).
     */
    public PersonResponseDto() {
    }

    /**
     * All-args constructor for creating a complete response DTO.
     *
     * @param id the unique identifier of the person
     * @param name the name of the person
     * @param age the age of the person
     * @param height the height of the person in meters
     */
    public PersonResponseDto(String id, String name, Integer age, Double height) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
    }

    // Getters and setters

    // equals() and hashCode()

    // toString()
}