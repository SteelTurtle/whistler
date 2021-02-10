package org.gorillacorp.whistler.cucumber;

import io.cucumber.java.en.And;
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
        assert(cucumberTestClient.httpGetUserAsEntity("dummy_user1") != null);
    }

    @Then("the client gets a HTTP 200 code as response")
    public void theClientGetsAHTTP200CodeAsResponse() {
        cucumberTestClient.httpGetStatusCodeIs200("dummy_user1", 200);
    }

    @And("the client gets a JSON payload with the requested user information")
    public void theClientGetsAJSONPayloadWithTheRequestedUserInformation() {
        var retrievedUser = cucumberTestClient.httpGetUserAsEntity("dummy_user1");
        assertEquals(retrievedUser.getUserName(), "dummy_user1");
        assertNotNull(retrievedUser.getId());
        cucumberTestClient.httpGetUserAsJson("dummy_user1");
    }
}
