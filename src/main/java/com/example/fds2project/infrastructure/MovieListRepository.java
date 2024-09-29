package com.example.fds2project.infrastructure;

import com.example.fds2project.domain.MovieList;
import com.example.fds2project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieListRepository extends JpaRepository<MovieList, Long> {
    MovieList findByUserAndName(User user, String name);
    List<MovieList> findByUser(User user);
}
