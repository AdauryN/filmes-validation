Feature: Favorite an Actor or Director

  Scenario: User successfully favorites an actor or director
    Given the user is authenticated as "testuser"
    And the actor or director "Adam Sandler" with ID 1 exists in the system
    When the user favorites the actor or director with ID 1
    Then the system should display the message "Adam Sandler has been added to your favorites!"
    And the name "Adam Sandler" should appear in the user's list of favorite people

Feature: Notification of New Works by Favorite Actors or Directors

  Scenario: User receives a notification when a new work by a favorite actor or director is added
    Given the user has "Christopher Nolan" favorited
    And a new work by "Christopher Nolan" is added to the catalog
    When the system processes the new work
    Then the user should receive a notification with the message "A new work by Christopher Nolan has been added: Oppenheimer"
    And the new work should appear in the user's notification list
