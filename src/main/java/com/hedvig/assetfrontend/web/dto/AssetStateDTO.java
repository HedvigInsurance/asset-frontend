package com.hedvig.assetfrontend.web.dto;

import com.hedvig.assetfrontend.constant.AssetState;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class AssetStateDTO {

    private String id;

    @NotNull
    private AssetState state;
}
