package com.CineMeetServer.service.eventRequest;


import com.CineMeetServer.dto.EventRequestDTO;
import com.CineMeetServer.entities.EventRequest;
import com.CineMeetServer.enums.RequestStatus;
import com.CineMeetServer.repo.EventRepository;
import com.CineMeetServer.repo.EventRequestRepository;
import com.CineMeetServer.repo.UserRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventRequestServiceImpl implements EventRequestService{

    @Autowired
    private EventRequestRepository eventRequestRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepo userRepository;


    public EventRequestDTO createEventRequest(EventRequestDTO requestDto) {
        Optional<EventRequest> optionalEventRequest = eventRequestRepository.findByUserIdAndEventId(requestDto.getUserId(), requestDto.getEventId());
        if(optionalEventRequest.isPresent()){
            throw new EntityExistsException("Request Already Exits.");
        }
        EventRequest request = new EventRequest();
        request.setStatus(RequestStatus.PENDING); // Default status
        request.setWhatBringing(requestDto.getWhatBringing());
        request.setEvent(eventRepository.findById(
                requestDto.getEventId()).orElseThrow(() -> new EntityNotFoundException("Event not found")));
        request.setUser(userRepository.findById(
                requestDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found")));

        EventRequest savedRequest = eventRequestRepository.save(request);
        return savedRequest.toDto();
    }

    public EventRequestDTO updateRequestStatus(Long id, RequestStatus status) {
        EventRequest request = eventRequestRepository.findById(id).orElseThrow();
        request.setStatus(status);

        EventRequest updatedRequest = eventRequestRepository.save(request);
        return updatedRequest.toDto();
    }

    public List<EventRequestDTO> getPendingEventRequests(Long id) {
        List<EventRequest> requests = eventRequestRepository.findByEventIdAndStatusIn(id, List.of(RequestStatus.PENDING, RequestStatus.DISAPPROVED));
        return requests.stream()
                .map(EventRequest::toDto)
                .collect(Collectors.toList());
    }

    public List<EventRequestDTO> getApprovedRequests(Long id) {
        List<EventRequest> requests = eventRequestRepository.findByEventIdAndStatus(id, RequestStatus.APPROVED);
        return requests.stream()
                .map(EventRequest::toDto)
                .collect(Collectors.toList());
    }

    public EventRequestDTO checkMyRequest(Long eventId, Long userId) {
        Optional<EventRequest> optionalEventRequest = eventRequestRepository.findByUserIdAndEventId(userId, eventId);
        if(optionalEventRequest.isPresent()){
            return optionalEventRequest.get().toDto();
        }
        throw new EntityNotFoundException("Request Not Found.");
    }
}
