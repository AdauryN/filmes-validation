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
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        Assertions.assertNotNull(mockMvc, "MockMvc should be initialized.");
    }

    @When("a user submits valid event details")
    public void aUserSubmitsValidEventDetails() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDTO)))
                .andExpect(status().isOk())
                .andReturn();

        retrievedEventResponse = result.getResponse().getContentAsString();
    }

    @Then("the event is created")
    public void theEventIsCreated() throws Exception {
        EventDTO createdEvent = objectMapper.readValue(retrievedEventResponse, EventDTO.class);

        Assertions.assertNotNull(createdEvent, "The created event should not be null.");
        Assertions.assertEquals("Movie Night", createdEvent.getTitle(), "The event title should match.");
        Assertions.assertEquals("Inception", createdEvent.getMovieName(), "The movie name should match.");
    }

    @And("the user can retrieve the event by its ID")
    public void theUserCanRetrieveTheEventByItsID() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/event/1"))
                .andExpect(status().isOk())
                .andReturn();

        retrievedEventResponse = result.getResponse().getContentAsString();
        EventDTO retrievedEvent = objectMapper.readValue(retrievedEventResponse, EventDTO.class);

        Assertions.assertNotNull(retrievedEvent, "The retrieved event should not be null.");
        Assertions.assertEquals("Sample Event", retrievedEvent.getTitle(), "The event title should match.");
    }

    @When("a user tries to retrieve an event with an invalid ID")
    public void aUserTriesToRetrieveAnEventWithAnInvalidID() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/event/999"))
                .andReturn();

        retrievalSuccessful = result.getResponse().getStatus() == 200;
    }

    @Then("the system returns an error")
    public void theSystemReturnsAnError() {
        Assertions.assertFalse(retrievalSuccessful, "Retrieving an event with an invalid ID should fail.");
    }
}