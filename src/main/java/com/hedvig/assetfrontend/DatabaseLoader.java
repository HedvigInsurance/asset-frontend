package com.hedvig.assetfrontend;

import com.hedvig.assetfrontend.constant.AssetState;
import com.hedvig.assetfrontend.domain.Asset;
import com.hedvig.assetfrontend.domain.Login;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Prepare test data, this is only needed in development.
 */

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;

    public DatabaseLoader(EntityManager entityManager, PasswordEncoder passwordEncoder) {
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... strings) {
        val login = new Login("victor@hedvig.com", passwordEncoder.encode("123"));
        entityManager.persist(login);

        for (int i = 0; i < 100; i++) {
            val id = UUID.randomUUID().toString();
            val asset = new Asset(
                    id,
                    "http://thecatapi.com/?id=60m",
                    "http://thecatapi.com/?id=3hn",
                    "Asset number " + i,
                    AssetState.PENDING,
                    i % 2 == 0,
                    "user-id-" + (i % 10),
                    LocalDate.now()
            );
            entityManager.persist(asset);
        }
    }
}
