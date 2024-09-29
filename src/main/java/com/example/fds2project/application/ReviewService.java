package com.example.fds2project.application;

import com.example.fds2project.domain.Movie;
import com.example.fds2project.domain.Review;
import com.example.fds2project.domain.User;
import com.example.fds2project.infrastructure.MovieRepository;
import com.example.fds2project.infrastructure.ReviewRepository;
import com.example.fds2project.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewService(
            ReviewRepository reviewRepository,
            UserRepository userRepository,
            MovieRepository movieRepository
    ) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    // Method to submit a review
    public void submitReview(String username, String movieTitle, int rating, String content) {
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

        Review review = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setRating(rating);
        review.setContent(content);
        reviewRepository.save(review);
    }

    // Method to get reviews for a movie
    public List<Review> getReviewsForMovie(String movieTitle) {
        Movie movie = movieRepository.findByTitle(movieTitle);
        if (movie == null) {
            throw new IllegalArgumentException("Movie not found");
        }
        return reviewRepository.findByMovie(movie);
    }
}
