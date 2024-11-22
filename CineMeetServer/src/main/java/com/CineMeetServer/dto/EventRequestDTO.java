package com.CineMeetServer.dto;

import com.CineMeetServer.enums.RequestStatus;
import lombok.Data;

@Data
public class EventRequestDTO {

    private Long id;
    private RequestStatus status;
    private String whatBringing;
    private Long eventId;
    private Long userId;
    private String userName;

    private String userEmail;
}
