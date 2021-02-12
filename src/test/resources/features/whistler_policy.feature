Feature: Users management and operations

  @get-user
  Scenario: A client makes a request to the API to get a known user
    When the client calls the /users endpoint looking for an existing user
    Then the client gets a HTTP 200 code as response
    And the client gets a JSON payload with the requested user information

  @create-user
  Scenario: A client post a valid JSON payload to create a new user:
    Given a valid JSON payload
    When the client send a POST request to the /user endpoint
    Then a new user is created on the system
    And the response contains a HTTP 200 code and a JSON payload containing the new user