package com.example.fds2project.bdd;

import com.example.fds2project.application.ReviewService;
import com.example.fds2project.application.UserService;
import com.example.fds2project.domain.Review;
import com.example.fds2project.domain.User;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReviewSteps {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

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

    @When("the user writes a review {string} with a rating of {int} for the movie {string}")
    public void the_user_writes_a_review_with_a_rating_of_for_the_movie(String content, int rating, String movieTitle) {
        try {
            reviewService.submitReview(testUser.getUsername(), movieTitle, rating, content);
            responseMessage = "Review submitted successfully!";
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

    @Then("the review should be visible for the movie {string}")
    public void the_review_should_be_visible_for_the_movie(String movieTitle) {
        List<Review> reviews = reviewService.getReviewsForMovie(movieTitle);
        boolean found = reviews.stream().anyMatch(review -> review.getUser().equals(testUser) && review.getContent().equals("Amazing movie!"));
        Assert.assertTrue(found);
    }
}
