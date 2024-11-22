package com.CineMeetServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EventDTO {

    private Long id;

    private String title;

    private Date date;

    private String description;
    private String movieName;
    private String movieImgUrl;
    private String userName;
    private Long userId;
}
