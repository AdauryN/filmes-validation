Feature: Movie Review

  Scenario: User writes a review for a movie
    Given the user is authenticated as "testuser"
    When the user writes a review "Amazing movie!" with a rating of 5 for the movie "Inception"
    Then the system should display the message "Review submitted successfully!"
    And the review should be visible for the movie "Inception"
