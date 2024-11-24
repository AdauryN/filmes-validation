package com.CineMeetServer.steps;

import com.CineMeetServer.controller.ReviewController;
import com.CineMeetServer.dto.ReviewDTO;
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

@WebMvcTest(ReviewController.class)
public class ReviewManagementSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ReviewDTO reviewDTO;
    private String retrievedReviewResponse;

    @Before
    public void setUp() {
        reviewDTO = new ReviewDTO();
        reviewDTO.setId(1L);
        reviewDTO.setEventId(1L);
        reviewDTO.setReviewerId(123L);
        reviewDTO.setReviewerName("John Doe");
        reviewDTO.setReview("Great event!");
        reviewDTO.setRating(5L); // Explicitly using Long
    }

    @Given("an event exists")
    public void anEventExists() {
        Assertions.assertNotNull(reviewDTO.getEventId(), "Event ID should not be null.");
    }

    @When("a user submits a review for the event")
    public void aUserSubmitsAReviewForTheEvent() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewDTO)))
                .andExpect(status().isOk())
                .andReturn();

        retrievedReviewResponse = result.getResponse().getContentAsString();
    }

    @Then("the review is saved")
    public void theReviewIsSaved() throws Exception {
        ReviewDTO savedReview = objectMapper.readValue(retrievedReviewResponse, ReviewDTO.class);

        Assertions.assertNotNull(savedReview, "The saved review should not be null.");
        Assertions.assertEquals("Great event!", savedReview.getReview(), "The review content should match.");
        Assertions.assertEquals(5L, savedReview.getRating(), "The review rating should match.");
    }

    @And("the user can retrieve the review")
    public void theUserCanRetrieveTheReview() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/review/1"))
                .andExpect(status().isOk())
                .andReturn();

        retrievedReviewResponse = result.getResponse().getContentAsString();
        ReviewDTO retrievedReview = objectMapper.readValue(retrievedReviewResponse, ReviewDTO.class);

        Assertions.assertNotNull(retrievedReview, "The retrieved review should not be null.");
        Assertions.assertEquals("Great event!", retrievedReview.getReview(), "The review content should match.");
    }
}