package com.CineMeetServer.controller;

import com.CineMeetServer.dto.EventDTO;
import com.CineMeetServer.service.event.EventService;
import com.CineMeetServer.service.friend.FriendService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
public class EventController {


    @Autowired
    private EventService eventService;

    // 1. Send Friend Request
    @PostMapping()
    public ResponseEntity<?> sendFriendRequest(@RequestBody EventDTO dto) {
        try{
            return ResponseEntity.ok(eventService.createEvent(dto));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getFutureEvents() {
        try{
            return ResponseEntity.ok(eventService.getFutureEvents());
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/cercle/{id}")
    public ResponseEntity<?> getFriendsEvents(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(eventService.getFriendsEvents(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getEvent(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(eventService.getEvent(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}
