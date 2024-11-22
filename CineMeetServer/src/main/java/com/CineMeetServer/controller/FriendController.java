package com.CineMeetServer.controller;

import com.CineMeetServer.dto.FriendDTO;
import com.CineMeetServer.entities.Friend;
import com.CineMeetServer.enums.FriendStatus;
import com.CineMeetServer.service.friend.FriendService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    // 1. Send Friend Request
    @PostMapping("/send-request")
    public ResponseEntity<?> sendFriendRequest(@RequestParam Long userId, @RequestParam Long friendId) {
        try{
            return ResponseEntity.ok(friendService.sendFriendRequest(userId, friendId));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    // 2. Get Friend Requests for User
    @GetMapping("/requests")
    public ResponseEntity<List<FriendDTO>> getFriendRequests(@RequestParam Long userId) {
        List<FriendDTO> requests = friendService.getFriendRequests(userId);
        return ResponseEntity.ok(requests);
    }

    // 3. Get Friends of User
    @GetMapping("/list")
    public ResponseEntity<List<FriendDTO>> getFriends(@RequestParam Long userId) {
        List<FriendDTO> friends = friendService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    // 4. Accept or Reject Friend Request
    @PostMapping("/respond")
    public ResponseEntity<?> respondToFriendRequest(@RequestParam Long requestId, @RequestParam FriendStatus status) {
        try{
            return ResponseEntity.ok(friendService.respondToFriendRequest(requestId, status));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}
