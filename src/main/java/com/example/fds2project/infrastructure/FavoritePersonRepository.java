package com.example.fds2project.infrastructure;

import com.example.fds2project.domain.FavoritePerson;
import com.example.fds2project.domain.User;
import com.example.fds2project.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritePersonRepository extends JpaRepository<FavoritePerson, Long> {
    List<FavoritePerson> findByUser(User user);
    FavoritePerson findByUserAndPerson(User user, Person person);
    List<FavoritePerson> findByPerson(Person person);
}
