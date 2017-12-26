package com.hedvig.assetfrontend.aggregates;

import com.hedvig.assetfrontend.common.commands.AssetStateChangeCommand;
import com.hedvig.assetfrontend.common.events.AssetStateChangeEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class AssetStateChange {

    @AggregateIdentifier
    private String id;

    @CommandHandler
    public AssetStateChange(AssetStateChangeCommand command) {
        apply(new AssetStateChangeEvent(command.getId()));
    }

    @EventSourcingHandler
    public void on(AssetStateChangeEvent event) {
        this.id = event.getId();
    }

}
