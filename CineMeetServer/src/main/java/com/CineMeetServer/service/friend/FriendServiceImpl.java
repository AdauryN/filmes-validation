package com.CineMeetServer.service.friend;

import com.CineMeetServer.dto.FriendDTO;
import com.CineMeetServer.entities.Friend;
import com.CineMeetServer.entities.User;
import com.CineMeetServer.enums.FriendStatus;
import com.CineMeetServer.repo.FriendRepository;
import com.CineMeetServer.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService{

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepo userRepository;

    public FriendDTO sendFriendRequest(Long userId, Long friendId) {
        if (userId.equals(friendId)) {
            throw new EntityNotFoundException("Cannot send friend request to yourself.");
        }

        Optional<User> user = userRepository.findById(userId);
        Optional<User> friend = userRepository.findById(friendId);

        if (user.isEmpty() || friend.isEmpty()) {
            throw new EntityNotFoundException("User not found.");
        }

        // Check if there's already a friend request or friendship
        if (friendRepository.existsByUserAndFriend(user.get(), friend.get()) || friendRepository.existsByUserAndFriend(friend.get(), user.get())) {
            throw new EntityNotFoundException("Friend request already exists.");
        }

        Friend friendRequest = new Friend();
        friendRequest.setFriend(friend.get());
        friendRequest.setUser(user.get());
        friendRequest.setStatus(FriendStatus.PENDING);
        return friendRepository.save(friendRequest).getDto();
    }

    public List<FriendDTO> getFriendRequests(Long userId) {
        List<FriendStatus> statuses = Arrays.asList(FriendStatus.PENDING, FriendStatus.REJECTED);
        return friendRepository.findFriendsByUserIdAndStatus(userId, statuses).stream().map(Friend::getDto).collect(Collectors.toList());
    }

    public List<FriendDTO> getFriends(Long userId) {
        return friendRepository.findByUserIdAndStatusOrFriendIdAndStatus(userId, FriendStatus.ACCEPTED, userId, FriendStatus.ACCEPTED)
                .stream().map(Friend::getDto).collect(Collectors.toList());
    }

    public FriendDTO respondToFriendRequest(Long requestId, FriendStatus status) {
        Optional<Friend> friendRequest = friendRepository.findById(requestId);

        if (friendRequest.isEmpty() || friendRequest.get().getStatus() != FriendStatus.PENDING) {
            throw new EntityNotFoundException("Friend request not found or already processed.");
        }

        friendRequest.get().setStatus(status);
        return friendRepository.save(friendRequest.get()).getDto();

//        return (status == FriendStatus.ACCEPTED) ? "Friend request accepted." : "Friend request rejected.";
    }
}
