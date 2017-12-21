package com.hedvig.assetfrontend.web;

import com.hedvig.assetfrontend.domain.User;
import com.hedvig.assetfrontend.security.AuthorizationException;
import com.hedvig.assetfrontend.security.JWTDTO;
import com.hedvig.assetfrontend.security.JWTService;
import com.hedvig.assetfrontend.services.login.UserService;
import com.hedvig.assetfrontend.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private JWTService jwtService;

    @Autowired
    public UserController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/login")
    public JWTDTO login(@RequestBody @Valid UserDTO dto, HttpServletResponse response) throws AuthorizationException {
        User user = userService.authorizeUser(dto.getEmail(), dto.getPassword());
        JWTDTO jwt = jwtService.createTokenForUser(user.getEmail());
        jwtService.addTokenToHeader(jwt.getToken(), response);

        return jwt;
    }

}
