package org.gorillacorp.whistler.domain.repository;

import org.gorillacorp.whistler.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(final String username);
}
