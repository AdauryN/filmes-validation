package com.CineMeetServer.repo;

import com.CineMeetServer.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDateAfter(Date date);


    List<Event> findByDateAfterAndUserIdIn(Date date, List<Long> friends);
}
