package com.example.fds2project.bdd;

import com.example.fds2project.application.UserService;
import com.example.fds2project.application.WatchPartyService;
import com.example.fds2project.domain.User;
import com.example.fds2project.domain.WatchParty;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class WatchPartySteps {

    @Autowired
    private UserService userService;

    @Autowired
    private WatchPartyService watchPartyService;

    private User testUser;
    private String responseMessage;
    private Exception exception;

    @Given("the user is authenticated as {string}")
    public void the_user_is_authenticated_as(String username) {
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

    @When("the user creates a watch party named {string} for the movie {string} on {string}")
    public void the_user_creates_a_watch_party_named_for_the_movie_on(String partyName, String movieTitle, String dateTime) {
        try {
            LocalDateTime eventDateTime = LocalDateTime.parse(dateTime.replace(" ", "T"));
            watchPartyService.createWatchParty(testUser.getUsername(), partyName, movieTitle, eventDateTime);
            responseMessage = "Watch Party '" + partyName + "' created successfully!";
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

    @Then("the watch party {string} should appear in the user's events")
    public void the_watch_party_should_appear_in_the_user_s_events(String partyName) {
        List<WatchParty> events = watchPartyService.getUserWatchParties(testUser.getUsername());
        boolean found = events.stream().anyMatch(wp -> wp.getName().equals(partyName));
        Assert.assertTrue(found);
    }

    @Then("the watch party should have the movie {string} and date {string}")
    public void the_watch_party_should_have_the_movie_and_date(String movieTitle, String dateTime) {
        List<WatchParty> events = watchPartyService.getUserWatchParties(testUser.getUsername());
        LocalDateTime eventDateTime = LocalDateTime.parse(dateTime.replace(" ", "T"));
        boolean found = events.stream().anyMatch(wp ->
                wp.getMovieTitle().equals(movieTitle) && wp.getDateTime().equals(eventDateTime)
        );
        Assert.assertTrue(found);
    }
}
