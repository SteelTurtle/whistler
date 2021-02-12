package org.gorillacorp.whistler.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.commons.lang3.RandomStringUtils;
import org.gorillacorp.whistler.domain.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@CucumberContextConfiguration
public class CucumberTestClient {

    private final WebTestClient webTestClient = WebTestClient
            .bindToServer()
            .baseUrl("http://localhost:9090")
            .build();

    public User httpGetUserAsEntity(final String username) {
        return webTestClient
                .get()
                .uri("users/" + username)
                .exchange()
                .expectBody(User.class)
                .returnResult()
                .getResponseBody();
    }

    public void httpGetStatusCodeIs200(final String username, final int statusCode) {
        webTestClient
                .get()
                .uri("users/" + username)
                .exchange()
                .expectStatus()
                .isEqualTo(statusCode);
    }

    public void httpGetUserAsJson(final String username) {
        webTestClient
                .get()
                .uri("users/" + username)
                .exchange()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.userName").isEqualTo("dummy_user1")
                .consumeWith(System.out::println);
    }

    public void httpPostNewUser() {
        var username = RandomStringUtils.randomAlphabetic(8);
        webTestClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(
                        """
                                {
                                    "userName": " """ + username + """
                                "}
                                """.trim()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.userName").isEqualTo(username)
                .consumeWith(System.out::println);
    }
}
