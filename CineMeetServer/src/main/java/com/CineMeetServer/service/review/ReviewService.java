package com.CineMeetServer.service.review;

import com.CineMeetServer.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    ReviewDTO giveReview(ReviewDTO reviewDTO);

    List<ReviewDTO> getAllReviewsOfUser(Long userId);
}
