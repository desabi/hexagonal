package com.desabi.guide.hexagonal.crud.mongodb.hexagonal_demo_a.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Person domain model.
 * Pure domain entity with no framework dependencies.
 * Contains business logic and domain rules.
 */
@Data
@AllArgsConstructor // All-args constructor (for retrieval from database)
@NoArgsConstructor
public class Person {

    private String id;
    private String name;
    private Integer age;
    private Double height;

    // Constructor without ID (for creation)
    public Person(String name, Integer age, Double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    // Business methods (optional - domain behavior examples)
    
    /**
     * Checks if the person is an adult.
     * @return true if age is 18 or greater
     */
    public boolean isAdult() {
        return this.age != null && this.age >= 18;
    }

    /**
     * Checks if the person is a minor.
     * @return true if age is less than 18
     */
    public boolean isMinor() {
        return !isAdult();
    }
}