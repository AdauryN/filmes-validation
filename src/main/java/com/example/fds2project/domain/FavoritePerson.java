package com.example.fds2project.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "favorite_persons")
public class FavoritePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reference to the user who favorited the person
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Reference to the favorited person
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    // Constructors
    public FavoritePerson() {}

    public FavoritePerson(User user, Person person) {
        this.user = user;
        this.person = person;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Person getPerson() {
        return person;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
