package com.example.fds2project.presentation;

import com.example.fds2project.application.UserService;
import com.example.fds2project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/favorite-persons/{personId}")
    public User favoritePerson(@PathVariable Long userId, @PathVariable Long personId) {
        return userService.addFavoritePerson(userId, personId);
    }
}
