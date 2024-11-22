package com.CineMeetServer.dto;

import com.CineMeetServer.enums.FriendStatus;
import lombok.Data;

@Data
public class FriendDTO {

    private Long id;

    private FriendStatus status;


    private Long userId;
    private String userName;
    private String userEmail;
    private Long friendId;
    private String friendName;
    private String friendEmail;
}
