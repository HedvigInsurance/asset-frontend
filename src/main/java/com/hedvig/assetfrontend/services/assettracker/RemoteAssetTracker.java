package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.domain.Asset;
import com.hedvig.assetfrontend.web.dto.AssetDTO;
import com.hedvig.common.commands.AssetStateChangeCommand;
import com.hedvig.common.constant.AssetState;
import lombok.val;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RemoteAssetTracker implements AssetTracker {

    private static Logger logger = LoggerFactory.getLogger(RemoteAssetTracker.class);

    private CommandGateway commandGateway;
    private String trackerUrl;
    private String assetsPath;

    @Autowired
    public RemoteAssetTracker(CommandBus commandBus,
                              @Value("${tracker.baseUrl}") String trackerUrl,
                              @Value("${tracker.urls.assets}") String assetsPath) {
        this.commandGateway = new DefaultCommandGateway(commandBus);
        this.trackerUrl = trackerUrl;
        this.assetsPath = assetsPath;
    }

    @Override
    public List<Asset> findPendingAssets() {
        RestTemplate template = new RestTemplate();
        try {
            ResponseEntity<AssetDTO[]> response = template.getForEntity(trackerUrl + assetsPath, AssetDTO[].class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return Arrays.stream(response.getBody()).map(AssetDTO::toDomain).collect(Collectors.toList());
            } else {
                logger.error("Assets are not fetched from tracker");
            }
        } catch (Exception e) {
            logger.error("Assets are not fetched from tracker", e);
        }

        return new ArrayList<>();
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
