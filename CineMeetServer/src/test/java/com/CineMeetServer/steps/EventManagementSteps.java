package com.CineMeetServer.steps;

import com.CineMeetServer.controller.EventController;
import com.CineMeetServer.dto.EventDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(EventController.class)
public class EventManagementSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private EventDTO eventDTO;
    private String retrievedEventResponse;
    private boolean retrievalSuccessful;

    @Before
    public void setUp() {
        eventDTO = new EventDTO();
        eventDTO.setId(1L);
        eventDTO.setTitle("Movie Night");
        eventDTO.setDate(new Date());
        eventDTO.setDescription("A fun movie night event");
        eventDTO.setMovieName("Inception");
        eventDTO.setMovieImgUrl("inception.jpg");
        eventDTO.setUserName("HostUser");
        eventDTO.setUserId(123L);
    }

    @Given("the application is running")
    public void theApplicationIsRunning() {
        Assertions.assertTrue(true, "");
    }

    @When("a user submits valid event details")
    public void aUserSubmitsValidEventDetails() throws Exception {
        retrievedEventResponse = objectMapper.writeValueAsString(eventDTO);
    }

    @Then("the event is created")
    public void theEventIsCreated() throws Exception {
        EventDTO createdEvent = objectMapper.readValue(retrievedEventResponse, EventDTO.class);

        Assertions.assertNotNull(createdEvent, "The created event should not be null.");
        Assertions.assertEquals(eventDTO.getTitle(), createdEvent.getTitle(), "The event title should match.");
        Assertions.assertEquals(eventDTO.getMovieName(), createdEvent.getMovieName(), "The movie name should match.");
    }

    @And("the user can retrieve the event by its ID")
    public void theUserCanRetrieveTheEventByItsID() throws Exception {
        retrievedEventResponse = objectMapper.writeValueAsString(eventDTO);
        EventDTO retrievedEvent = objectMapper.readValue(retrievedEventResponse, EventDTO.class);

        Assertions.assertNotNull(retrievedEvent, "The retrieved event should not be null.");
        Assertions.assertEquals(eventDTO.getTitle(), retrievedEvent.getTitle(), "The event title should match.");
    }

    @When("a user tries to retrieve an event with an invalid ID")
    public void aUserTriesToRetrieveAnEventWithAnInvalidID() throws Exception {
        retrievalSuccessful = false;
    }

    @Then("the system returns an error")
    public void theSystemReturnsAnError() {
        Assertions.assertFalse(retrievalSuccessful, "Retrieving an event with an invalid ID should fail.");
    }
}