package com.example.fds2project.domain;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    // Default constructor
    public User() {
    }

    @ManyToMany
    @JoinTable(
            name = "user_favorite_persons",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> favoritePersons = new HashSet<>();

    // Getters and setters for favoritePersons
    public Set<Person> getFavoritePersons() {
        return favoritePersons;
    }

    public void setFavoritePersons(Set<Person> favoritePersons) {
        this.favoritePersons = favoritePersons;
    }

    // Constructor with parameters
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getter and Setter for 'id'
    public Long getId() {
        return id;
    }

    // No setter for 'id' since it's generated

    // Getter and Setter for 'username'
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for 'password'
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for 'email'
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
