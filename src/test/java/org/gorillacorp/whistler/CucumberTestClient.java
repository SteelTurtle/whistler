package org.gorillacorp.whistler;

import io.cucumber.spring.CucumberContextConfiguration;
import org.gorillacorp.whistler.domain.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.test.web.reactive.server.WebTestClient;

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
}
