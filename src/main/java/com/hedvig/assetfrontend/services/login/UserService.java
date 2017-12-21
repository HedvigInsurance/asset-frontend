package com.hedvig.assetfrontend.services.login;

import com.hedvig.assetfrontend.domain.User;
import com.hedvig.assetfrontend.security.AuthorizationException;

public interface UserService {

    User authorizeUser(String email, String password) throws AuthorizationException;

}
