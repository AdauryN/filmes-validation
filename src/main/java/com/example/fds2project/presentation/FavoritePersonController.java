package com.example.fds2project.presentation;

import com.example.fds2project.application.FavoritePersonService;
import com.example.fds2project.domain.FavoritePerson;
import com.example.fds2project.domain.Person;
import com.example.fds2project.infrastructure.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favorites")
public class FavoritePersonController {

    private final FavoritePersonService favoritePersonService;
    private final PersonRepository personRepository;

    @Autowired
    public FavoritePersonController(
            FavoritePersonService favoritePersonService,
            PersonRepository personRepository
    ) {
        this.favoritePersonService = favoritePersonService;
        this.personRepository = personRepository;
    }

    // Endpoint to favorite a person by ID
    @PostMapping("/{personId}")
    public ResponseEntity<?> favoritePerson(
            @PathVariable Long personId,
            Authentication authentication
    ) {
        try {
            String username = authentication.getName();
            String message = favoritePersonService.favoritePerson(username, personId);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint to get the list of favorite persons for the authenticated user
    @GetMapping
    public ResponseEntity<List<String>> getFavoritePersons(Authentication authentication) {
        String username = authentication.getName();
        List<FavoritePerson> favoritePersons = favoritePersonService.getFavoritePersons(username);
        List<String> personNames = favoritePersons.stream()
                .map(fav -> fav.getPerson().getName())
                .collect(Collectors.toList());
        return ResponseEntity.ok(personNames);
    }

    // Endpoint to add a Person to the database (for testing purposes)
    @PostMapping("/addPerson")
    public ResponseEntity<?> addPerson(@RequestBody Person person) {
        personRepository.save(person);
        return ResponseEntity.ok("Person added successfully");
    }
}
