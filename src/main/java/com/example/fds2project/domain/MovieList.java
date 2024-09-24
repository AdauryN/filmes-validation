package com.example.fds2project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MovieList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String privacySetting;

    // Constructors, getters, and setters

    public MovieList() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrivacySetting() {
        return privacySetting;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrivacySetting(String privacySetting) {
        this.privacySetting = privacySetting;
    }
}
