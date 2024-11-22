package com.CineMeetServer.entities;

import com.CineMeetServer.dto.EventDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "events")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date date;

    @Column(length = 1000)
    private String description;
    private String movieName;
    private String movieImgUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public EventDTO getDto(){
        EventDTO dto = new EventDTO();

        dto.setId(id);
        dto.setTitle(title);
        dto.setDescription(description);
        dto.setDate(date);
        dto.setMovieName(movieName);
        dto.setMovieImgUrl(movieImgUrl);
        dto.setUserId(user.getId());
        dto.setUserName(user.getName());

        return dto;
    }
}
