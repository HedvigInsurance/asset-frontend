package com.hedvig.assetfrontend.common.events;

import lombok.Value;
import org.axonframework.commandhandling.model.AggregateIdentifier;

@Value
public class AssetStateChangeEvent {

    @AggregateIdentifier
    private String id;

}
