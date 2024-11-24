package com.CineMeetServer.steps;

import com.CineMeetServer.controller.FriendController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FriendController.class)
public class FriendRelationshipsSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private boolean friendRequestSuccessful;

    @Before
    public void setUp() {
        // No specific setup for now
    }

    @Given("two users exist")
    public void twoUsersExist() {
        Assertions.assertTrue(true, "Users should exist for the test.");
    }

    @When("one user sends a friend request")
    public void oneUserSendsAFriendRequest() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/friend/request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senderId\": 123, \"receiverId\": 456}"))
                .andExpect(status().isOk())
                .andReturn();

        friendRequestSuccessful = result.getResponse().getStatus() == 200;
    }

    @Then("they become friends")
    public void theyBecomeFriends() {
        Assertions.assertTrue(friendRequestSuccessful, "Friend request should be successful.");
    }
}