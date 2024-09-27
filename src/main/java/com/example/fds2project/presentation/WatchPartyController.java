package com.example.fds2project.presentation;

import com.example.fds2project.application.WatchPartyService;
import com.example.fds2project.domain.WatchParty;
import com.example.fds2project.domain.User;
import com.example.fds2project.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/watch-parties")
public class WatchPartyController {

    @Autowired
    private WatchPartyService watchPartyService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public WatchParty createWatchParty(@RequestBody WatchParty watchParty, @RequestParam Set<Long> participantIds) {
        Set<User> participants = userRepository.findAllById(participantIds).stream().collect(Collectors.toSet());
        watchParty.setParticipants(participants);
        return watchPartyService.createWatchParty(watchParty);
    }

    // Additional endpoints
}
