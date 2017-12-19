package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.constant.AssetState;
import com.hedvig.assetfrontend.domain.Asset;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LocalAssetTracker implements AssetTracker {

    private static Logger logger = LoggerFactory.getLogger(LocalAssetTracker.class);

    private final AssetTrackerService assetTrackerService;

    private boolean generated = false;

    @Autowired
    public LocalAssetTracker(AssetTrackerService assetTrackerService) {
        this.assetTrackerService = assetTrackerService;
    }

    @Override
    public void findPendingAssets() {
        if (!generated) {
            logger.info("fetch pending assets");
            generated = true;
            List<Asset> assets = IntStream.range(0, 100).mapToObj(i -> {
                val id = UUID.randomUUID().toString();
                return new Asset(
                        id,
                        "http://thecatapi.com/?id=60m",
                        "http://thecatapi.com/?id=3hn",
                        "Asset number " + i,
                        AssetState.PENDING,
                        i % 2 == 0,
                        "user-id-" + (i % 10),
                        LocalDate.now()
                );
            }).collect(Collectors.toList());

            assetTrackerService.loadPendingAssets(assets);
        }
    }

    @Override
    public boolean updateAssetState(String id, AssetState state) {
        return true;
    }

}
