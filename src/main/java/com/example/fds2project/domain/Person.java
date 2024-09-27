package com.example.fds2project.domain;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

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

}
