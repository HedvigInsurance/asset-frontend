package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.common.commands.AssetStateChangeCommand;
import com.hedvig.assetfrontend.constant.AssetState;
import com.hedvig.assetfrontend.domain.Asset;
import lombok.val;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class RemoteAssetTracker implements AssetTracker {

    private CommandGateway commandGateway;

    @Autowired
    public RemoteAssetTracker(CommandBus commandBus) {
        commandGateway = new DefaultCommandGateway(commandBus);
    }

    @Override
    public List<Asset> findPendingAssets() {
        throw new RuntimeException("Not implemented yet!");
    }

    @Override
    public void updateAssetState(String assetId, AssetState state) throws AssetTrackerException {
        val aggregateId = UUID.randomUUID().toString();
        try {
            commandGateway.sendAndWait(new AssetStateChangeCommand(aggregateId, assetId, state));
        } catch (CommandExecutionException ex) {
            throw new AssetTrackerException("");
        }
    }

}
