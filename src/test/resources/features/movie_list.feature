Feature: Movie List Management

  Scenario: Create a new movie list
    Given a user wants to create a movie list named "Favorites"
    When the user sets the privacy to "public"
    And saves the movie list
    Then the movie list should be created successfully
    And assigned an ID
