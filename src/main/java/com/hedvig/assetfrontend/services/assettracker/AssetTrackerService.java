package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.constant.AssetState;
import com.hedvig.assetfrontend.domain.Asset;
import com.hedvig.assetfrontend.repository.AssetRepository;
import org.axonframework.commandhandling.CommandBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
public class AssetTrackerService {

    private static Logger logger = LoggerFactory.getLogger(AssetTrackerService.class);

    private final AssetRepository assetRepository;
    private final AssetTracker tracker;

    @Autowired
    public AssetTrackerService(AssetRepository assetRepository, AssetTracker tracker) {
        this.assetRepository = assetRepository;
        this.tracker = tracker;
    }

    public void loadPendingAssets(List<Asset> assets) {
        if (assets.size() > 0) {
            assetRepository.save(assets);
            logger.info("Pending assets added");
        }
    }

    public void loadPendingAssetsFromTracker() {
        loadPendingAssets(tracker.findPendingAssets());
    }

    public Stream<Asset> findPendingAssets() {
        return assetRepository.streamAll();
    }

    public Asset find(String assetId) throws AssetNotFoundException {
        Asset asset = assetRepository.findOne(assetId);
        if (asset == null) {
            throw new AssetNotFoundException(String.format("asset with id %s not found", assetId));
        }

        return asset;
    }

    @Transactional
    public void changeAssetState(String assetId, AssetState state) throws AssetNotFoundException {
        Asset asset = assetRepository.findOne(assetId);
        if (asset != null) {
            if (!tracker.updateAssetState(assetId, state)) {
                throw new AssetNotFoundException(String.format("asset with id %s not found", assetId));
            }

            asset.setState(state);
            assetRepository.save(asset);
            logger.info(String.format("state for asset with id %s changed to %s", assetId, state.name()));
        } else {
            throw new AssetNotFoundException(String.format("asset with id %s not found", assetId));
        }
    }
}
