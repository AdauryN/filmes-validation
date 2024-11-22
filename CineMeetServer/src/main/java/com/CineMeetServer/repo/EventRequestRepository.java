package com.CineMeetServer.repo;

import com.CineMeetServer.entities.EventRequest;
import com.CineMeetServer.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRequestRepository extends JpaRepository<EventRequest, Long> {

    Optional<EventRequest> findByUserIdAndEventId(Long userId, Long eventId);

    List<EventRequest> findByEventIdAndStatusIn(Long eventId, List<RequestStatus> statuses);

    List<EventRequest> findByEventIdAndStatus(Long eventId, RequestStatus status);
}
