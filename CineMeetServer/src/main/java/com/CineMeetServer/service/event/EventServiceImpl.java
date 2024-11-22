package com.CineMeetServer.service.event;

import com.CineMeetServer.dto.EventDTO;
import com.CineMeetServer.dto.FriendDTO;
import com.CineMeetServer.entities.Event;
import com.CineMeetServer.entities.Friend;
import com.CineMeetServer.entities.User;
import com.CineMeetServer.enums.FriendStatus;
import com.CineMeetServer.repo.EventRepository;
import com.CineMeetServer.repo.FriendRepository;
import com.CineMeetServer.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepo userRepository;

    public EventDTO createEvent(EventDTO dto){
        Optional<User> optionalUser = userRepository.findById(dto.getUserId());
        if(optionalUser.isPresent()){
            Event event = new Event();

            event.setTitle(dto.getTitle());
            event.setDescription(dto.getDescription());
            event.setDate(dto.getDate());
            event.setMovieName(dto.getMovieName());
            event.setMovieImgUrl(dto.getMovieImgUrl());
            event.setUser(optionalUser.get());

            event = eventRepository.save(event);

            User user = optionalUser.get();
            user.setEventsHosted(user.getEventsHosted()+1);
            userRepository.save(user);

            return event.getDto();
        }
        throw new EntityNotFoundException("User not found");

    }

    public List<EventDTO> getFutureEvents() {
        return eventRepository.findByDateAfter(new Date()).stream().map(Event::getDto).collect(Collectors.toList());
    }

    public List<EventDTO> getFriendsEvents(Long userId) {
        List<Friend> friends = friendRepository.findByUserIdAndStatusOrFriendIdAndStatus(userId, FriendStatus.ACCEPTED, userId, FriendStatus.ACCEPTED);

        List<Long> longList = friends.stream()
                .map(friend -> {
                    if (Objects.equals(friend.getUser().getId(), userId)) {
                        return friend.getFriend().getId();
                    }
                    return friend.getUser().getId();
                })
                .toList();

        return eventRepository.findByDateAfterAndUserIdIn(new Date(), longList).stream().map(Event::getDto).collect(Collectors.toList());
    }

    public EventDTO getEvent(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if(optionalEvent.isPresent()){
            return optionalEvent.get().getDto();
        }else{
            throw new EntityNotFoundException("Event not found");
        }
    }
}
