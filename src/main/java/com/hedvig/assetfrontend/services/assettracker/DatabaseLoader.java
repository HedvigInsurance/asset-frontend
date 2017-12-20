package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.constant.AssetState;
import com.hedvig.assetfrontend.domain.Asset;
import com.hedvig.assetfrontend.domain.Login;
import lombok.val;
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
public class DatabaseLoader {

    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public DatabaseLoader(EntityManager entityManager, PasswordEncoder passwordEncoder) {
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser() {
        val login = new Login("victor@hedvig.com", passwordEncoder.encode("123"));
        entityManager.persist(login);
    }

    @Transactional
    public void loadData() {
        for (int i = 0; i < 15; i++) {
            val id = UUID.randomUUID().toString();
            val asset = new Asset(
                    id,
                    "http://78.media.tumblr.com/tumblr_ll313eVnI91qjahcpo1_1280.jpg",
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
