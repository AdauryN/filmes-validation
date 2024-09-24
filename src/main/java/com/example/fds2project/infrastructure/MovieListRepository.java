package com.example.fds2project.infrastructure;

import com.example.fds2project.domain.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieListRepository extends JpaRepository<MovieList, Long>, MovieListRepositoryCustom {
    // Define query methods using method naming conventions if needed
    List<MovieList> findByPrivacySetting(String privacySetting);
}
