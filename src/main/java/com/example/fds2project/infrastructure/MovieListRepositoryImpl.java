package com.example.fds2project.infrastructure;

import com.example.fds2project.domain.MovieList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MovieListRepositoryImpl implements MovieListRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void customMethodExample() {
    }
}
