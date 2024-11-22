package com.CineMeetServer.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String email;

    private String name;

    private Double rating;
    private Long eventsHosted;
}
