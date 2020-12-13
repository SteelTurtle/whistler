package org.gorillacorp.whistler.domain.repository;

import org.gorillacorp.whistler.domain.model.User;
import org.gorillacorp.whistler.domain.model.Whistle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface WhistleRepository extends JpaRepository<Whistle, Long> {
    Optional<Collection<Whistle>> findAllByAuthor(final User user);
    Optional<Collection<Whistle>> findByAuthor_UserNameOrWhistleContains(final String username, final String mention);
}
