package com.example.fds2project.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "watch_parties")
public class WatchParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime dateTime;

    // Relationship with User (Host)
    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    // Relationship with Movie
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // Constructors
    public WatchParty() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public User getHost() {
        return host;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
