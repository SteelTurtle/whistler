package org.gorillacorp.whistler.domain.service;

import lombok.AllArgsConstructor;
import org.gorillacorp.whistler.domain.model.User;
import org.gorillacorp.whistler.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Scheduler scheduler;

    @Transactional(readOnly = true)
    public Mono<User> findByUsername(final String username) {
        return Mono.fromCallable(() -> userRepository.findByUserName(username)).publishOn(scheduler);
    }

    // unused service methods for this implementation:
    @Transactional(rollbackFor = Exception.class)
    public Mono<User> saveUser(final User user) {
        return Mono.fromCallable(() -> userRepository.save(user)).publishOn(scheduler);
    }

    @Transactional(readOnly = true)
    public Mono<User> findByUserId(final Long id) {
        return Mono.fromCallable(() -> userRepository
                .findById(id)
                .orElse(new User("unknown_user"))
        ).publishOn(scheduler);
    }
}
