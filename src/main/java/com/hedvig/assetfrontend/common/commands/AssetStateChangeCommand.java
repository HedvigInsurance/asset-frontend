package com.hedvig.assetfrontend.common.commands;

import com.hedvig.assetfrontend.constant.AssetState;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class AssetStateChangeCommand {

    @TargetAggregateIdentifier
    private String id;

    private String assetId;

    private AssetState state;

}
