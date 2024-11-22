package com.CineMeetServer.repo;


import com.CineMeetServer.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByOwnerId(Long id);

    Optional<Review> findByEventIdAndReviewerId(Long eventId, Long reviewerId);

}
