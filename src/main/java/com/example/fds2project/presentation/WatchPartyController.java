package com.example.fds2project.presentation;

import com.example.fds2project.application.WatchPartyService;
import com.example.fds2project.domain.WatchParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/watch-parties")
public class WatchPartyController {

    @Autowired
    private WatchPartyService watchPartyService;

    // Endpoint to create a watch party
    @PostMapping("/create")
    public ResponseEntity<?> createWatchParty(
            @RequestParam String partyName,
            @RequestParam String movieTitle,
            @RequestParam String dateTime, // Expecting format "yyyy-MM-ddTHH:mm"
            Authentication authentication) {
        String username = authentication.getName();
        try {
            LocalDateTime eventDateTime = LocalDateTime.parse(dateTime);
            watchPartyService.createWatchParty(username, partyName, movieTitle, eventDateTime);
            return ResponseEntity.ok("Watch Party created successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to get the user's watch parties
    @GetMapping
    public ResponseEntity<?> getUserWatchParties(Authentication authentication) {
        String username = authentication.getName();
        List<WatchParty> watchParties = watchPartyService.getUserWatchParties(username);
        return ResponseEntity.ok(watchParties);
    }

    // Additional endpoints can be added as needed
}
