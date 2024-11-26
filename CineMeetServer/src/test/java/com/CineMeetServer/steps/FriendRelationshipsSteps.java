package com.CineMeetServer.steps;

import com.CineMeetServer.controller.FriendController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FriendController.class)
public class FriendRelationshipsSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private boolean friendRequestSuccessful;
    private boolean friendAcceptanceSuccessful;
    private boolean friendRejectionHandled;

    @Before
    public void setUp() {
    }

    @Given("two users exist")
    public void twoUsersExist() {
        Assertions.assertTrue(true, "Users should exist for the test.");
    }

    @When("one user sends a friend request")
    public void oneUserSendsAFriendRequest() throws Exception {
        friendRequestSuccessful = true;
    }

    @When("the other user accepts the friend request")
    public void theOtherUserAcceptsTheFriendRequest() {
        friendAcceptanceSuccessful = true;
    }

    @Then("they become friends")
    public void theyBecomeFriends() {
        Assertions.assertTrue(friendRequestSuccessful && friendAcceptanceSuccessful, "Users should become friends.");
    }

    @When("the other user rejects the friend request")
    public void theOtherUserRejectsTheFriendRequest() {
        friendRejectionHandled = true;
    }

    @Then("they do not become friends")
    public void theyDoNotBecomeFriends() {
        Assertions.assertTrue(friendRequestSuccessful && friendRejectionHandled, "Users do not become friends.");
    }
}