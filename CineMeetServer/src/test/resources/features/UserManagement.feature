Feature: User Management

  Scenario: Successful User Signup
    Given the application is running
    When the user submits valid registration details
    Then the user account is created
    And the user can log in with the provided credentials

  Scenario: Login with invalid credentials
    Given the application is running
    When the user submits invalid login credentials
    Then the login attempt is rejected