package com.example.fds2project.infrastructure;

import com.example.fds2project.domain.User;
import com.example.fds2project.domain.WatchParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchPartyRepository extends JpaRepository<WatchParty, Long> {
    List<WatchParty> findByHost(User host);
}
