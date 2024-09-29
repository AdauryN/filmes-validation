package com.example.fds2project.presentation;

import com.example.fds2project.application.MovieListService;
import com.example.fds2project.domain.MovieList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie-lists")
public class MovieListController {

    private final MovieListService service;

    @Autowired
    public MovieListController(MovieListService service) {
        this.service = service;
    }

    // Endpoint to create a new movie list
    @PostMapping("/create")
    public ResponseEntity<?> createMovieList(@RequestParam String listName, Authentication authentication) {
        String username = authentication.getName();
        try {
            MovieList createdList = service.createList(username, listName);
            return ResponseEntity.ok(createdList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to add a movie to a list
    @PostMapping("/add-movie")
    public ResponseEntity<?> addMovieToList(
            @RequestParam String listName,
            @RequestParam String movieTitle,
            Authentication authentication) {
        String username = authentication.getName();
        try {
            service.addMovieToList(username, listName, movieTitle);
            return ResponseEntity.ok("Movie added to the list successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to get all lists of the authenticated user
    @GetMapping
    public ResponseEntity<?> getUserLists(Authentication authentication) {
        String username = authentication.getName();
        List<MovieList> lists = service.getUserLists(username);
        return ResponseEntity.ok(lists);
    }

    // Endpoint to get a specific list by name
    @GetMapping("/{listName}")
    public ResponseEntity<?> getListByName(@PathVariable String listName, Authentication authentication) {
        String username = authentication.getName();
        MovieList list = service.getListByName(username, listName);
        if (list != null) {
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Additional endpoints can be added as needed
}
