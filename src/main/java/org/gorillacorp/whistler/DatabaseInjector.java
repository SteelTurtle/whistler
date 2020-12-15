package org.gorillacorp.whistler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.gorillacorp.whistler.domain.model.User;
import org.gorillacorp.whistler.domain.model.Whistle;
import org.gorillacorp.whistler.domain.repository.UserRepository;
import org.gorillacorp.whistler.domain.repository.WhistleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
public class DatabaseInjector implements CommandLineRunner {

    private final UserRepository userRepository;
    private final WhistleRepository whistleRepository;

    @Override
    public void run(String... args) {
        try {
            val user1 = new User("dummy_user1");
            val user2 = new User("dummy_user2");
            val user3 = new User("dummy_user3");
            user2.addFollowing(user1);
            user1.addFollowing(user3);
            userRepository.saveAll(List.of(user1, user2, user3));
            whistleRepository.save(new Whistle(user1, "@dummy_user1 balalalalala"));
            whistleRepository.save(new Whistle(user2, "From dummy_user_2: Hi @dummy_user1!"));
            whistleRepository.save(new Whistle(user3, "From dummy_user3: @dummy_user1 lorem ipsum"));
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

    }
}
