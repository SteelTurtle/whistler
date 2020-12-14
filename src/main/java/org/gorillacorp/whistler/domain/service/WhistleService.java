package org.gorillacorp.whistler.domain.service;

import lombok.AllArgsConstructor;
import org.gorillacorp.whistler.domain.model.User;
import org.gorillacorp.whistler.domain.model.Whistle;
import org.gorillacorp.whistler.domain.repository.WhistleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@Transactional
@AllArgsConstructor
public class WhistleService {

    private final WhistleRepository whistleRepository;
    private final Scheduler scheduler;

    @Transactional(readOnly = true)
    public Flux<Whistle> findAllByAuthor(final User user) {
        return Flux.fromIterable(whistleRepository.findAllByAuthor(user)).publishOn(scheduler);
    }

    @Transactional(rollbackFor = Exception.class)
    public Mono<Whistle> saveWhistle(final Whistle whistle) {
        return Mono.fromCallable(() -> whistleRepository.save(whistle)).publishOn(scheduler);
    }

    @Transactional(readOnly = true)
    public Flux<Whistle> findTaggedWhistles(final String username) {
        return Flux.fromIterable(whistleRepository
                .findByAuthor_UserNameOrWhistleContains(username, "@" + username))
                .publishOn(scheduler);
    }

    @Transactional(readOnly = true)
    public Flux<Whistle> findAllWhistles() {
        return Flux.fromIterable(whistleRepository.findAll()).publishOn(scheduler);
    }
}
