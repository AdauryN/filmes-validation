package com.example.fds2project.infrastructure;

import com.example.fds2project.domain.MovieList;
import java.util.List;
import java.util.Optional;

public interface MovieListRepository {
    MovieList save(MovieList movieList);
    Optional<MovieList> findById(Long id);
    List<MovieList> findAll();
    void deleteById(Long id);
}
