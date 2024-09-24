//package com.example.fds2project.infrastructure;
//
//import com.example.fds2project.domain.MovieList;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class InMemoryMovieListRepository implements MovieListRepository {
//    private Map<Long, MovieList> store = new HashMap<>();
//    private Long currentId = 1L;
//
//    @Override
//    public MovieList save(MovieList movieList) {
//        if (movieList.getId() == null) {
//            movieList.setId(currentId++);
//        }
//        store.put(movieList.getId(), movieList);
//        return movieList;
//    }
//
//    @Override
//    public Optional<MovieList> findById(Long id) {
//        return Optional.ofNullable(store.get(id));
//    }
//
//    @Override
//    public List<MovieList> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        store.remove(id);
//    }
//}
