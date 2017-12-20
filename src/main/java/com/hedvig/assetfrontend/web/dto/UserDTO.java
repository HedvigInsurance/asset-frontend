package com.hedvig.assetfrontend.web.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class UserDTO {

    @NotNull
    private String email;

    @NotNull
    private String password;

}
