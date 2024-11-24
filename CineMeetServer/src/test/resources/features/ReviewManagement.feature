Feature: Review Management

  Scenario: Submit a Review for an Event
    Given an event exists
    When a user submits a review for the event
    Then the review is saved
    And the user can retrieve the review

  Scenario: Submit an invalid review
    Given an event exists
    When a user submits an invalid review
    Then the system rejects the review submission