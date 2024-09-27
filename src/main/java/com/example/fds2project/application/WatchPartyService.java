package com.example.fds2project.application;

import com.example.fds2project.domain.WatchParty;
import com.example.fds2project.infrastructure.WatchPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchPartyService {

    private final WatchPartyRepository watchPartyRepository;

    @Autowired
    public WatchPartyService(WatchPartyRepository watchPartyRepository) {
        this.watchPartyRepository = watchPartyRepository;
    }

    public WatchParty createWatchParty(WatchParty watchParty) {
        return watchPartyRepository.save(watchParty);
    }

    // Additional methods
}
