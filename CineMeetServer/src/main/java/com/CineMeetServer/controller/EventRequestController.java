package com.CineMeetServer.controller;

import com.CineMeetServer.dto.EventDTO;
import com.CineMeetServer.dto.EventRequestDTO;
import com.CineMeetServer.enums.RequestStatus;
import com.CineMeetServer.service.event.EventService;
import com.CineMeetServer.service.eventRequest.EventRequestService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-request")
public class EventRequestController {

    @Autowired
    private EventRequestService eventRequestService;

    // 1. Send Friend Request
    @PostMapping()
    public ResponseEntity<?> createEventRequest(@RequestBody EventRequestDTO dto) {
        try{
            return ResponseEntity.ok(eventRequestService.createEventRequest(dto));
        } catch (EntityNotFoundException | EntityExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/update-status/{id}/{status}")
    public ResponseEntity<?> updateRequestStatus(@PathVariable Long id, @PathVariable RequestStatus status) {
        try{
            return ResponseEntity.ok(eventRequestService.updateRequestStatus(id, status));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/check-status/{userId}/{eventId}")
    public ResponseEntity<?> checkMyRequest(@PathVariable Long userId, @PathVariable Long eventId) {
        try{
            return ResponseEntity.ok(eventRequestService.checkMyRequest(eventId, userId));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/pending-or-canceled/{id}")
    public ResponseEntity<?> getPendingOrCanceledRequests(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(eventRequestService.getPendingEventRequests(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/approved/{id}")
    public ResponseEntity<?> getApprovedRequests(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(eventRequestService.getApprovedRequests(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}
