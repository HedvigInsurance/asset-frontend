package com.hedvig.assetfrontend.services.login;

import com.hedvig.assetfrontend.services.assettracker.DatabaseLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class LoginService {

    private final DatabaseLoader databaseLoader;

    @Autowired
    public LoginService(DatabaseLoader databaseLoader) {
        this.databaseLoader = databaseLoader;
    }

    @PostConstruct
    public void setup() {
        databaseLoader.createUser();
    }

}
