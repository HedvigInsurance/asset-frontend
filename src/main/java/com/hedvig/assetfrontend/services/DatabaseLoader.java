package com.hedvig.assetfrontend.services;

import com.hedvig.assetfrontend.domain.User;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * Prepare test data, this is only needed in development.
 */

@Component
public class DatabaseLoader {

    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public DatabaseLoader(EntityManager entityManager, PasswordEncoder passwordEncoder) {
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser() {
        val login = new User("victor@hedvig.com", passwordEncoder.encode("123"));
        entityManager.persist(login);
    }
}
