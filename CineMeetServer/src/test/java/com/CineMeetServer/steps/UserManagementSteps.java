package com.CineMeetServer.steps;

import com.CineMeetServer.controller.UserController;
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

@WebMvcTest(UserController.class)
public class UserManagementSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private boolean loginSuccessful;

    @Before
    public void setUp() {
        // No specific setup for now
    }

    @When("the user submits valid registration details")
    public void theUserSubmitsValidRegistrationDetails() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"test@example.com\", \"password\": \"password123\"}"))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus(), "User registration should succeed.");
    }

    @And("the user can log in with the provided credentials")
    public void theUserCanLogInWithTheProvidedCredentials() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"test@example.com\", \"password\": \"password123\"}"))
                .andExpect(status().isOk())
                .andReturn();

        loginSuccessful = result.getResponse().getStatus() == 200;
    }

    @Then("the login attempt is successful")
    public void theLoginAttemptIsSuccessful() {
        Assertions.assertTrue(loginSuccessful, "Login attempt should succeed.");
    }
}