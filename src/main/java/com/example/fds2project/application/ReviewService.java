package com.example.fds2project.application;

import com.example.fds2project.domain.Review;
import com.example.fds2project.domain.User;
import com.example.fds2project.infrastructure.ReviewRepository;
import com.example.fds2project.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public Review createReview(Review review, Long authorId) {
        User author = userRepository.findById(authorId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        review.setAuthor(author);
        return reviewRepository.save(review);
    }

    // Additional methods
}
