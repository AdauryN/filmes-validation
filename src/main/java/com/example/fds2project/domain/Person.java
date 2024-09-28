package com.example.fds2project.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    private Long id; // Assuming this matches an external API ID

    private String name;

    private String role; // "actor" or "director"

    // Constructors
    public Person() {
    }

    public Person(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    // Add getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
