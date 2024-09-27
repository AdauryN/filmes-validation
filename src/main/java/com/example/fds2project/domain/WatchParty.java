package com.example.fds2project.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

@Entity
public class WatchParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieTitle;

    private LocalDateTime scheduledTime;

    @ManyToMany
    @JoinTable(
            name = "watchparty_participants",
            joinColumns = @JoinColumn(name = "watchparty_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants = new HashSet<>();

    // Constructors
    public WatchParty() {
    }

    public WatchParty(String movieTitle, LocalDateTime scheduledTime) {
        this.movieTitle = movieTitle;
        this.scheduledTime = scheduledTime;
    }

    // Getter and Setter for 'participants'
    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    // Getters and setters for other fields
    public Long getId() {
        return id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
