package com.example.fds2project.presentation;

import com.example.fds2project.application.MovieListService;
import com.example.fds2project.domain.MovieList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<MovieList> createMovieList(@RequestBody MovieList movieList) {
        MovieList created = service.createMovieList(movieList);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieList> getMovieList(@PathVariable Long id) {
        return service.getMovieList(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MovieList>> getAllMovieLists() {
        return ResponseEntity.ok(service.getAllMovieLists());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieList> updateMovieList(@PathVariable Long id, @RequestBody MovieList movieList) {
        movieList.setId(id);
        MovieList updated = service.updateMovieList(movieList);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieList(@PathVariable Long id) {
        service.deleteMovieList(id);
        return ResponseEntity.noContent().build();
    }
}
