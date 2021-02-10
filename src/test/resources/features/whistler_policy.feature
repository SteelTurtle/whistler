Feature: Retrieving system users by their username

  Scenario: A client makes a request to the API to get a known user
    When the client calls the /users endpoint looking for an existing user
    Then the client gets a HTTP 200 code as response
    And the client gets a JSON payload with the requested user information