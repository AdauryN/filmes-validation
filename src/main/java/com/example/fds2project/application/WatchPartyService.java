package com.example.fds2project.application;

import com.example.fds2project.domain.Movie;
import com.example.fds2project.domain.User;
import com.example.fds2project.domain.WatchParty;
import com.example.fds2project.infrastructure.MovieRepository;
import com.example.fds2project.infrastructure.UserRepository;
import com.example.fds2project.infrastructure.WatchPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WatchPartyService {

    private final WatchPartyRepository watchPartyRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public WatchPartyService(
            WatchPartyRepository watchPartyRepository,
            UserRepository userRepository,
            MovieRepository movieRepository
    ) {
        this.watchPartyRepository = watchPartyRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    // Method to create a watch party
    public void createWatchParty(String username, String partyName, String movieTitle, LocalDateTime dateTime) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Movie movie = movieRepository.findByTitle(movieTitle);
        if (movie == null) {
            movie = new Movie();
            movie.setTitle(movieTitle);
            movieRepository.save(movie);
        }

        WatchParty watchParty = new WatchParty();
        watchParty.setName(partyName);
        watchParty.setHost(user);
        watchParty.setMovie(movie);
        watchParty.setDateTime(dateTime);
        watchPartyRepository.save(watchParty);
    }

    // Method to get watch parties for a user
    public List<WatchParty> getUserWatchParties(String username) {
        User user = userRepository.findByUsername(username);
        return watchPartyRepository.findByHost(user);
    }
}
