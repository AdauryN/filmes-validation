package com.CineMeetServer.service.event;

import com.CineMeetServer.dto.EventDTO;

import java.util.List;

public interface EventService {

    EventDTO createEvent(EventDTO dto);

    List<EventDTO> getFutureEvents();

    List<EventDTO> getFriendsEvents(Long userId);

    EventDTO getEvent(Long id);
}
