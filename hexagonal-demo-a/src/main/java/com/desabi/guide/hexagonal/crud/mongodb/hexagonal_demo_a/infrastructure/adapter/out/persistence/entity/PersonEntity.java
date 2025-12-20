package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * MongoDB entity representing a person in the database.
 * This class is specific to the persistence layer and contains MongoDB annotations.
 * Should not be exposed to the domain or application layers.
 */
@Document(collection = "persons")
@Data
public class PersonEntity {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("age")
    private Integer age;

    @Field("height")
    private Double height;

    /**
     * Default constructor (required by Spring Data MongoDB).
     */
    public PersonEntity() {
    }

    /**
     * Constructor without ID (for new entities).
     *
     * @param name the name of the person
     * @param age the age of the person
     * @param height the height of the person in meters
     */
    public PersonEntity(String name, Integer age, Double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    /**
     * All-args constructor (for existing entities).
     *
     * @param id the unique identifier
     * @param name the name of the person
     * @param age the age of the person
     * @param height the height of the person in meters
     */
    public PersonEntity(String id, String name, Integer age, Double height) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
    }

    // Getters and setters

    // equals() and hashCode()

    // toString()
}