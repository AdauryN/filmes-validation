package com.example.fds2project.presentation;

import com.example.fds2project.application.ReviewService;
import com.example.fds2project.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public Review createReview(@RequestBody Review review, @RequestParam Long authorId) {
        return reviewService.createReview(review, authorId);
    }

    // Additional endpoints
}
