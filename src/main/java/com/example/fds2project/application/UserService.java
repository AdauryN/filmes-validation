package com.example.fds2project.application;

import com.example.fds2project.domain.Person;
import com.example.fds2project.domain.User;
import com.example.fds2project.infrastructure.UserRepository;
import com.example.fds2project.infrastructure.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User registerUser(User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Encode the password
        user.setPassword(encoder.encode(user.getPassword()));

        // Save the user
        return userRepository.save(user);
    }

    @Autowired
    private PersonRepository personRepository;

    public User addFavoritePerson(Long userId, Long personId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Person person = personRepository.findById(personId).orElseThrow(() -> new IllegalArgumentException("Person not found"));
        user.getFavoritePersons().add(person);
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Additional methods for authentication if needed
}
