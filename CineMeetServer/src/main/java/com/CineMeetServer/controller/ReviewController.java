package com.CineMeetServer.controller;

import com.CineMeetServer.dto.EventDTO;
import com.CineMeetServer.dto.ReviewDTO;
import com.CineMeetServer.service.event.EventService;
import com.CineMeetServer.service.review.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {


    @Autowired
    private ReviewService reviewService;

    // 1. Send Friend Request
    @PostMapping()
    public ResponseEntity<?> giveReview(@RequestBody ReviewDTO dto) {
        try{
            return ResponseEntity.ok(reviewService.giveReview(dto));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllReviewsOfUser(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(reviewService.getAllReviewsOfUser(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
}
