package com.example.fds2project.application;

import com.example.fds2project.domain.FavoritePerson;
import com.example.fds2project.domain.Person;
import com.example.fds2project.domain.User;
import com.example.fds2project.infrastructure.FavoritePersonRepository;
import com.example.fds2project.infrastructure.PersonRepository;
import com.example.fds2project.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritePersonService {

    private final FavoritePersonRepository favoritePersonRepository;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    @Autowired
    public FavoritePersonService(
            FavoritePersonRepository favoritePersonRepository,
            PersonRepository personRepository,
            UserRepository userRepository
    ) {
        this.favoritePersonRepository = favoritePersonRepository;
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    public String favoritePerson(String username, Long personId) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Person person = personRepository.findById(personId).orElse(null);
        if (person == null) {
            throw new IllegalArgumentException("Person not found");
        }

        // Check if the person is already favorited by the user
        FavoritePerson existingFavorite = favoritePersonRepository.findByUserAndPerson(user, person);
        if (existingFavorite != null) {
            throw new IllegalArgumentException("Person is already in your favorites");
        }

        FavoritePerson favoritePerson = new FavoritePerson(user, person);
        favoritePersonRepository.save(favoritePerson);
        return person.getName() + " foi adicionado aos seus favoritos!";
    }

    public List<FavoritePerson> getFavoritePersons(String username) {
        User user = userRepository.findByUsername(username);
        return favoritePersonRepository.findByUser(user);
    }
}
