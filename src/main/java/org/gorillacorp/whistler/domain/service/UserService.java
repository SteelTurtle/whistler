package org.gorillacorp.whistler.domain.service;

import lombok.AllArgsConstructor;
import org.gorillacorp.whistler.domain.model.User;
import org.gorillacorp.whistler.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findByUsername(final String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UnknownUserException("I cannot find any user with username: " + username));
    }

    @Transactional(readOnly = true)
    public User findByUserId(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UnknownUserException("I cannot find any user with id: " + id));
    }
}
