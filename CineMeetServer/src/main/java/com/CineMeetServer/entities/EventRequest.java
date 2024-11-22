package com.CineMeetServer.entities;

import com.CineMeetServer.dto.EventRequestDTO;
import com.CineMeetServer.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "event_requests")
@Data
public class EventRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status; // Status of the request (PENDING, APPROVED, DISAPPROVED)

    @Column(length = 500)
    private String whatBringing; // What the user is bringing to the event

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event; // Associated event

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Associated user

    public EventRequestDTO toDto() {
        EventRequestDTO dto = new EventRequestDTO();
        dto.setId(id);
        dto.setStatus(status);
        dto.setWhatBringing(whatBringing);
        dto.setEventId(event.getId());
        dto.setUserId(user.getId());
        dto.setUserName(user.getName());
        dto.setUserEmail(user.getEmail());
        return dto;
    }
}
