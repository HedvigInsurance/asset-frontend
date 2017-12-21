package com.hedvig.assetfrontend.services.login;

import com.hedvig.assetfrontend.domain.User;
import com.hedvig.assetfrontend.repository.UserRepository;
import com.hedvig.assetfrontend.security.AuthorizationException;
import com.hedvig.assetfrontend.services.DatabaseLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final DatabaseLoader databaseLoader;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(DatabaseLoader databaseLoader, PasswordEncoder passwordEncoder,
                           UserRepository userRepository) {
        this.databaseLoader = databaseLoader;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setup() {
        databaseLoader.createUser();
    }

    @Override
    public User authorizeUser(String email, String password) throws AuthorizationException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (!userOptional.isPresent()) {
            throw new AuthorizationException(String.format("user %s not found", email));
        }

        User user = userOptional.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new AuthorizationException("incorrect password");
        }
    }

}
