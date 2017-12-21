package com.hedvig.assetfrontend.security;

import lombok.Value;

@Value
public class JWTDTO {

    private String email;
    private String token;

}
