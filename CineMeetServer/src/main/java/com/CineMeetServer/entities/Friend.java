package com.CineMeetServer.entities;

import com.CineMeetServer.dto.FriendDTO;
import com.CineMeetServer.enums.FriendStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "friends")
@Data
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private FriendStatus status;

    public FriendDTO getDto(){
        FriendDTO dto = new FriendDTO();

        dto.setFriendId(friend.getId());
        dto.setFriendName(friend.getName());
        dto.setFriendEmail(friend.getEmail());
        dto.setStatus(status);
        dto.setUserId(user.getId());
        dto.setUserName(user.getName());
        dto.setUserEmail(user.getEmail());
        dto.setId(id);

        return dto;
    }
}
