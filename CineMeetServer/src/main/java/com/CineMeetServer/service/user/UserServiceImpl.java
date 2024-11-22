package com.CineMeetServer.service.user;


import com.CineMeetServer.dto.UserDTO;
import com.CineMeetServer.entities.User;
import com.CineMeetServer.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    public User createUser(User user) {
        user.setPassword(user.getPassword());
        user.setEventsHosted(0L);
        user.setRating(0.0);
        User userCreated = userRepo.save(user);
        if (userCreated == null)
            return  null;
        return userCreated;
    }

    public Boolean hasUserWithEmail(String email) {
        return userRepo.findFirstByEmail(email) != null;
    }

    public User login(User user) {
        Optional<User> dbUser = userRepo.findByEmail(user.getEmail());
        if(dbUser.isPresent() && user.getPassword().equals(dbUser.get().getPassword())) {
            return dbUser.get();
        }
        return null;
    }


    public User getUser(Long userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        return optionalUser.orElse(null);
    }

    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map(User::getDto).collect(Collectors.toList());
    }

    public User updateUser(Long userId, User user) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isPresent()){
            User savedUser = optionalUser.get();

            savedUser.setEmail(user.getEmail());
            return userRepo.save(savedUser);
        }
        return null;
    }
}
