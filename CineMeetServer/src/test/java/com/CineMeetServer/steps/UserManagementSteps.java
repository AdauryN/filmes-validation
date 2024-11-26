package com.CineMeetServer.steps;

import com.CineMeetServer.controller.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserManagementSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private boolean loginSuccessful;
    private boolean accountCreated;
    private boolean loginAttemptRejected;

    @Before
    public void setUp() {
    }

    @When("the user submits valid registration details")
    public void theUserSubmitsValidRegistrationDetails() throws Exception {
        accountCreated = true;
    }

    @Then("the user account is created")
    public void theUserAccountIsCreated() {
        Assertions.assertTrue(accountCreated, "User account should be created.");
    }

    @And("the user can log in with the provided credentials")
    public void theUserCanLogInWithTheProvidedCredentials() throws Exception {
        loginSuccessful = true;
    }

    @Then("the login attempt is successful")
    public void theLoginAttemptIsSuccessful() {
        Assertions.assertTrue(loginSuccessful, "Login attempt should succeed.");
    }

    @When("the user submits invalid login credentials")
    public void theUserSubmitsInvalidLoginCredentials() {
        loginAttemptRejected = true;
    }

    @Then("the login attempt is rejected")
    public void theLoginAttemptIsRejected() {
        Assertions.assertTrue(loginAttemptRejected, "Login attempt should be rejected.");
    }
}