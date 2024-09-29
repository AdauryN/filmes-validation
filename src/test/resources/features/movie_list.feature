Feature: Movie List Management

  Scenario: User creates a new movie list and adds a movie
    Given the user is authenticated as "testuser"
    When the user creates a new list named "My Favorites"
    And the user adds the movie "Inception" to the list "My Favorites"
    Then the system should display the message "List 'My Favorites' created successfully!"
    And the list "My Favorites" should appear in the user's lists
    And the movie "Inception" should be in the list "My Favorites"
