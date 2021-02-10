package org.gorillacorp.whistler;

import org.gorillacorp.whistler.domain.model.User;
import org.gorillacorp.whistler.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class WhistlerApiIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Users can create and save their own whistles in the DB")
    void usersCanSaveTheirOwnWhistles() {
        var user = userRepository.save(new User("dummy_user1"));

        assertNotNull(user);
    }
}
