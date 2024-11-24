Feature: Friend Relationships

  Scenario: Send and Accept a Friend Request
    Given two users exist
    When one user sends a friend request
    And the other user accepts the friend request
    Then they become friends

  Scenario: Reject a Friend Request
    Given two users exist
    When one user sends a friend request
    And the other user rejects the friend request
    Then they do not become friends