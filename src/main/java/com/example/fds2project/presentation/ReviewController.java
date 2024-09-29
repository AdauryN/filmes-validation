package com.example.fds2project.presentation;

import com.example.fds2project.application.ReviewService;
import com.example.fds2project.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Endpoint to submit a review
    @PostMapping("/submit")
    public ResponseEntity<?> submitReview(
            @RequestParam String movieTitle,
            @RequestParam int rating,
            @RequestParam String content,
            Authentication authentication) {
        String username = authentication.getName();
        try {
            reviewService.submitReview(username, movieTitle, rating, content);
            return ResponseEntity.ok("Review submitted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to get reviews for a movie
    @GetMapping("/{movieTitle}")
    public ResponseEntity<?> getReviewsForMovie(@PathVariable String movieTitle) {
        try {
            List<Review> reviews = reviewService.getReviewsForMovie(movieTitle);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Additional endpoints can be added as needed
}
