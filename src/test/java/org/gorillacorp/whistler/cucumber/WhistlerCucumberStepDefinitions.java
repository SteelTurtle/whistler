package org.gorillacorp.whistler.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AllArgsConstructor
public class WhistlerCucumberStepDefinitions {

    private final CucumberTestClient cucumberTestClient;
    private static final String TEST_USER = "dummy_user1";

    @When("the client calls the \\/users endpoint looking for an existing user")
    public void theClientCallsTheUsersEndpointLookingForAnExistingUser() {
        assert (cucumberTestClient.httpGetUserAsEntity(TEST_USER) != null);
    }

    @Then("the client gets a HTTP 200 code as response")
    public void theClientGetsAHTTP200CodeAsResponse() {
        cucumberTestClient.httpGetStatusCodeIs200(TEST_USER, 200);
    }

    @And("the client gets a JSON payload with the requested user information")
    public void theClientGetsAJSONPayloadWithTheRequestedUserInformation() {
        var retrievedUser = cucumberTestClient.httpGetUserAsEntity(TEST_USER);
        assertEquals(retrievedUser.getUserName(), TEST_USER);
        assertNotNull(retrievedUser.getId());
        cucumberTestClient.httpGetUserAsJson(TEST_USER);
    }

    @Given("a valid JSON payload")
    public void aValidJSONPayload() {
    }

    @When("the client send a POST request to the \\/user endpoint")
    public void theClientSendAPOSTRequestToTheUserEndpoint() {
    }

    @Then("a new user is created on the system")
    public void aNewUserIsCreatedOnTheSystem() {
        cucumberTestClient.httpPostNewUser();
    }

    @And("the response contains a HTTP 200 code and a JSON payload containing the new user")
    public void theResponseContainsAHTTPCodeAndAJSONPayloadContainingTheNewUser() {
    }
}
