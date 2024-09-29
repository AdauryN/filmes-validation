package com.example.fds2project.bdd;

import com.example.fds2project.application.MovieListService;
import com.example.fds2project.application.UserService;
import com.example.fds2project.domain.MovieList;
import com.example.fds2project.domain.User;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MovieListSteps {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieListService movieListService;

    private User testUser;
    private String responseMessage;
    private Exception exception;

    @Given("the user is authenticated as {string}")
    public void the_user_is_authenticated_as(String username) {
        // Register and authenticate the user
        User user = new User();
        user.setUsername(username);
        user.setPassword("password123");
        user.setEmail(username + "@example.com");
        try {
            testUser = userService.registerUser(user);
        } catch (Exception e) {
            testUser = userService.findByUsername(username);
        }
    }

    @When("the user creates a new list named {string}")
    public void the_user_creates_a_new_list_named(String listName) {
        try {
            MovieList movieList = movieListService.createList(testUser.getUsername(), listName);
            responseMessage = "List '" + listName + "' created successfully!";
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("the user adds the movie {string} to the list {string}")
    public void the_user_adds_the_movie_to_the_list(String movieTitle, String listName) {
        try {
            movieListService.addMovieToList(testUser.getUsername(), listName, movieTitle);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the system should display the message {string}")
    public void the_system_should_display_the_message(String expectedMessage) {
        if (exception != null) {
            Assert.assertEquals(expectedMessage, exception.getMessage());
        } else {
            Assert.assertEquals(expectedMessage, responseMessage);
        }
    }

    @Then("the list {string} should appear in the user's lists")
    public void the_list_should_appear_in_the_user_s_lists(String listName) {
        List<MovieList> userLists = movieListService.getUserLists(testUser.getUsername());
        boolean found = userLists.stream().anyMatch(list -> list.getName().equals(listName));
        Assert.assertTrue(found);
    }

    @Then("the movie {string} should be in the list {string}")
    public void the_movie_should_be_in_the_list(String movieTitle, String listName) {
        MovieList movieList = movieListService.getListByName(testUser.getUsername(), listName);
        boolean found = movieList.getMovies().stream().anyMatch(movie -> movie.getTitle().equals(movieTitle));
        Assert.assertTrue(found);
    }
}
