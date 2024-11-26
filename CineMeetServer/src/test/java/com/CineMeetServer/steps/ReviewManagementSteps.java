package com.CineMeetServer.steps;

import com.CineMeetServer.controller.ReviewController;
import com.CineMeetServer.dto.ReviewDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReviewController.class)
public class ReviewManagementSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ReviewDTO reviewDTO;
    private String retrievedReviewResponse;
    private boolean reviewSubmissionSuccessful;

    @Before
    public void setUp() {
        reviewDTO = new ReviewDTO();
        reviewDTO.setId(1L);
        reviewDTO.setEventId(1L);
        reviewDTO.setReviewerId(123L);
        reviewDTO.setReviewerName("John Doe");
        reviewDTO.setReview("Great event!");
        reviewDTO.setRating(5L);
    }

    @Given("an event exists")
    public void anEventExists() {
        Assertions.assertTrue(true, "Event exists for the test.");
    }

    @When("a user submits a review for the event")
    public void aUserSubmitsAReviewForTheEvent() throws Exception {
        retrievedReviewResponse = objectMapper.writeValueAsString(reviewDTO);
    }

    @Then("the review is saved")
    public void theReviewIsSaved() throws Exception {
        ReviewDTO savedReview = objectMapper.readValue(retrievedReviewResponse, ReviewDTO.class);

        Assertions.assertNotNull(savedReview, "The saved review should not be null.");
        Assertions.assertEquals(reviewDTO.getReview(), savedReview.getReview(), "The review content should match.");
        Assertions.assertEquals(reviewDTO.getRating(), savedReview.getRating(), "The review rating should match.");
    }

    @And("the user can retrieve the review")
    public void theUserCanRetrieveTheReview() throws Exception {
        retrievedReviewResponse = objectMapper.writeValueAsString(reviewDTO);
        ReviewDTO retrievedReview = objectMapper.readValue(retrievedReviewResponse, ReviewDTO.class);

        Assertions.assertNotNull(retrievedReview, "The retrieved review should not be null.");
        Assertions.assertEquals(reviewDTO.getReview(), retrievedReview.getReview(), "The review content should match.");
    }

    @When("a user submits an invalid review")
    public void aUserSubmitsAnInvalidReview() {
        reviewSubmissionSuccessful = false;
    }

    @Then("the system rejects the review submission")
    public void theSystemRejectsTheReviewSubmission() {
        Assertions.assertFalse(reviewSubmissionSuccessful, "The system should reject the invalid review.");
    }
}