package com.CineMeetServer.entities;

import com.CineMeetServer.dto.UserDTO;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private Double rating;
    private Long eventsHosted;

    public User() {
    }

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getEventsHosted() {
        return eventsHosted;
    }

    public void setEventsHosted(Long eventsHosted) {
        this.eventsHosted = eventsHosted;
    }

    public UserDTO getDto(){
        UserDTO dto = new UserDTO();

        dto.setId(id);
        dto.setEmail(email);
        dto.setName(name);
        dto.setRating(rating);
        dto.setEventsHosted(eventsHosted);

        return dto;
    }


}
