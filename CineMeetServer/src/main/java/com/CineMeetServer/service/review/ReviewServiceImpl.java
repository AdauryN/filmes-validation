package com.CineMeetServer.service.review;

import com.CineMeetServer.dto.EventDTO;
import com.CineMeetServer.dto.ReviewDTO;
import com.CineMeetServer.entities.Event;
import com.CineMeetServer.entities.Review;
import com.CineMeetServer.entities.User;
import com.CineMeetServer.repo.EventRepository;
import com.CineMeetServer.repo.ReviewRepository;
import com.CineMeetServer.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewDTO giveReview(ReviewDTO reviewDTO){
        Optional<Review> optionalReview = reviewRepository.findByEventIdAndReviewerId(reviewDTO.getEventId(), reviewDTO.getReviewerId());

        if(optionalReview.isEmpty()) {
            Optional<User> optionalUser = userRepository.findById(reviewDTO.getReviewerId());
            Optional<Event> optionalEvent = eventRepository.findById(reviewDTO.getEventId());

            if (optionalUser.isPresent() && optionalEvent.isPresent()) {
                Review review = new Review();

                review.setReviewDate(new Date());
                review.setReview(reviewDTO.getReview());
                review.setRating(reviewDTO.getRating());

                review.setEvent(optionalEvent.get());
                review.setReviewer(optionalUser.get());
                review.setOwner(optionalEvent.get().getUser());

                review = reviewRepository.save(review);

                // Retrieve all reviews for the ad
                List<Review> reviews = reviewRepository.findAllByOwnerId(optionalEvent.get().getUser().getId());

                // Calculate the average rating
                double averageRating = reviews.stream()
                        .mapToDouble(Review::getRating)
                        .average()
                        .orElse(0.0);

                User owner = optionalEvent.get().getUser();
                owner.setRating(averageRating);
                userRepository.save(owner);

                return review.getDto();
            }
            throw new EntityNotFoundException("User or Event not found");
        }
        else{
            throw new EntityNotFoundException("You have already review this event.");
        }
    }

    public List<ReviewDTO> getAllReviewsOfUser(Long userId) {
        return reviewRepository.findAllByOwnerId(userId).stream().map(Review::getDto).collect(Collectors.toList());
    }
}
