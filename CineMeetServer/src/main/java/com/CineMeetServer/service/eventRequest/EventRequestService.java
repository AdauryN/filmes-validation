package com.CineMeetServer.service.eventRequest;


import com.CineMeetServer.dto.EventRequestDTO;
import com.CineMeetServer.enums.RequestStatus;

import java.util.List;

public interface EventRequestService {

    EventRequestDTO createEventRequest(EventRequestDTO requestDto);

    EventRequestDTO updateRequestStatus(Long id, RequestStatus status);

    List<EventRequestDTO> getPendingEventRequests(Long id);

    List<EventRequestDTO> getApprovedRequests(Long id);

    EventRequestDTO checkMyRequest(Long eventId, Long userId);
}
