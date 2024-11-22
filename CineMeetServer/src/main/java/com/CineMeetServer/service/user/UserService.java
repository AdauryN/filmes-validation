package com.CineMeetServer.service.user;

import com.CineMeetServer.dto.UserDTO;
import com.CineMeetServer.entities.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    Boolean hasUserWithEmail(String email);

    User login(User user);

    User getUser(Long userId);

    User updateUser(Long userId, User user);

    List<UserDTO> getAllUsers();
}
