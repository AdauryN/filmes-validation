Feature: Event Management

  Scenario: Create and Retrieve an Event
    Given the application is running
    When a user submits valid event details
    Then the event is created
    And the user can retrieve the event by its ID

  Scenario: Retrieve a non-existent event
    Given the application is running
    When a user tries to retrieve an event with an invalid ID
    Then the system returns an error