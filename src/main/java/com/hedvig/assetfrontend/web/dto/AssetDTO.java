package com.hedvig.assetfrontend.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hedvig.assetfrontend.constant.AssetState;
import com.hedvig.assetfrontend.domain.Asset;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
public class AssetDTO {
    @NotNull
    String id;

    @NotNull
    String photoUrl;

    @NotNull
    String receiptUrl;

    @NotNull
    String title;

    @NotNull
    AssetState state;

    @NotNull
    Boolean includedInBasePackage;

    @NotNull
    String userId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate registrationDate;

    public static AssetDTO fromDomain(Asset asset) {
        return new AssetDTO(
                asset.getId(),
                asset.getPhotoUrl(),
                asset.getReceiptUrl(),
                asset.getTitle(),
                asset.getState(),
                asset.getIncludedInBasePackage(),
                asset.getUserId(),
                asset.getRegistrationDate());
    }
}
