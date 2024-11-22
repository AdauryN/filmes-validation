package com.CineMeetServer.service.friend;

import com.CineMeetServer.dto.FriendDTO;
import com.CineMeetServer.entities.Friend;
import com.CineMeetServer.enums.FriendStatus;

import java.util.List;

public interface FriendService {

    FriendDTO sendFriendRequest(Long userId, Long friendId);

    List<FriendDTO> getFriendRequests(Long userId);

    List<FriendDTO> getFriends(Long userId);

    FriendDTO respondToFriendRequest(Long requestId, FriendStatus status);
}
