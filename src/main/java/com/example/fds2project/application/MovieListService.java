package com.example.fds2project.application;

import com.example.fds2project.domain.Movie;
import com.example.fds2project.domain.MovieList;
import com.example.fds2project.domain.User;
import com.example.fds2project.infrastructure.MovieListRepository;
import com.example.fds2project.infrastructure.MovieRepository;
import com.example.fds2project.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieListService {

    private final MovieListRepository movieListRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public MovieListService(
            MovieListRepository movieListRepository,
            UserRepository userRepository,
            MovieRepository movieRepository
    ) {
        this.movieListRepository = movieListRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    // Method to create a new list
    public MovieList createList(String username, String listName) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Check if the list already exists
        MovieList existingList = movieListRepository.findByUserAndName(user, listName);
        if (existingList != null) {
            throw new IllegalArgumentException("List already exists");
        }

        MovieList movieList = new MovieList();
        movieList.setName(listName);
        movieList.setUser(user);
        return movieListRepository.save(movieList);
    }

    // Method to add a movie to a list
    public void addMovieToList(String username, String listName, String movieTitle) {
        User user = userRepository.findByUsername(username);
        MovieList movieList = movieListRepository.findByUserAndName(user, listName);
        if (movieList == null) {
            throw new IllegalArgumentException("List not found");
        }

        // Find or create the movie
        Movie movie = movieRepository.findByTitle(movieTitle);
        if (movie == null) {
            movie = new Movie();
            movie.setTitle(movieTitle);
            movieRepository.save(movie);
        }

        // Add the movie to the list
        movieList.getMovies().add(movie);
        movieListRepository.save(movieList);
    }

    // Method to get all lists of a user
    public List<MovieList> getUserLists(String username) {
        User user = userRepository.findByUsername(username);
        return movieListRepository.findByUser(user);
    }

    // Method to get a list by name
    public MovieList getListByName(String username, String listName) {
        User user = userRepository.findByUsername(username);
        return movieListRepository.findByUserAndName(user, listName);
    }
}
