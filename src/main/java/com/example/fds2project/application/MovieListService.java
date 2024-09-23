package com.example.fds2project.application;

import com.example.fds2project.domain.MovieList;
import com.example.fds2project.infrastructure.MovieListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieListService {

    private final MovieListRepository repository;

    @Autowired
    public MovieListService(MovieListRepository repository) {
        this.repository = repository;
    }

    public MovieList createMovieList(MovieList movieList) {
        return repository.save(movieList);
    }

    public Optional<MovieList> getMovieList(Long id) {
        return repository.findById(id);
    }

    public List<MovieList> getAllMovieLists() {
        return repository.findAll();
    }

    public void deleteMovieList(Long id) {
        repository.deleteById(id);
    }

    public MovieList updateMovieList(MovieList movieList) {
        return repository.save(movieList);
    }
}
