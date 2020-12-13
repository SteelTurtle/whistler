package org.gorillacorp.whistler;

import jdk.jfr.Description;
import org.gorillacorp.whistler.domain.model.User;
import org.gorillacorp.whistler.domain.repository.UserRepository;
import org.gorillacorp.whistler.domain.repository.WhistleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class WhistlerApiIntegrationTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WhistleRepository whistleRepository;

    @Test
    @Description("Users can create and save their own whistles in the DB")
    void usersCanSaveTheirOwnWhistles() {
        var user = userRepository.save(new User("dummy_user1"));
        assertNotNull(user);
    }
}
