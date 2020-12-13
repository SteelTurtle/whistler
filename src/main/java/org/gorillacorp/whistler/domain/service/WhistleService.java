package org.gorillacorp.whistler.domain.service;

import lombok.AllArgsConstructor;
import org.gorillacorp.whistler.domain.model.User;
import org.gorillacorp.whistler.domain.model.Whistle;
import org.gorillacorp.whistler.domain.repository.WhistleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

@Service
@Transactional
@AllArgsConstructor
public class WhistleService {

    private final WhistleRepository whistleRepository;

    @Transactional(readOnly = true)
    public Flux<Whistle> findAllByAuthor(final User user) {
        return Flux.fromIterable(
                whistleRepository.findAllByAuthor(user)
                        .orElseThrow(() -> new WhistleNotFoundException("Could not retrieve whistles for " +
                                "user" + user.getUserName()))
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveWhistle(final Whistle whistle) {
        whistleRepository.save(whistle);
    }

    @Transactional(readOnly = true)
    public Flux<Whistle> findTaggedWhistles(final String username) {
        return Flux.fromIterable(
                whistleRepository.findByAuthor_UserNameOrWhistleContains(username, "@" + username)
                        .orElseThrow(() -> new WhistleNotFoundException("Could not retrieve whistles " +
                                "tagging user: " + username))
        );
    }

    @Transactional(readOnly = true)
    public Flux<Whistle> findAllWhistles() {
        return Flux.fromIterable(whistleRepository.findAll());
    }
}
