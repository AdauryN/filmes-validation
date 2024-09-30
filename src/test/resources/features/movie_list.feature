Feature: Create Movie List

  Scenario: User successfully creates a movie list
    Given the user authenticates as "testuser"
    When the user clicks the "Create New List" button
    And the user fills the "list name" field with "My Favorites"
    And the user adds the movie "Inception" to the list
    And the user clicks the "Save List" button
    Then the system should display the message "List successfully created!"
    And the list "My Favorites" should appear on the user's lists page

Feature: Add Movies to a List

  Scenario: User adds a movie to an existing list
    Given the user is on the "Inception" movie ID
    And the user has a list called "My Favorites"
    When the user clicks the "Add to List" button
    And the user selects "My Favorites"
    Then the system should display the message "Movie added to the list!"
    And the movie "Inception" should be in the "My Favorites" list

