package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.domain.Asset;
import com.hedvig.common.constant.AssetState;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LocalAssetTracker implements AssetTracker {

    private static Logger logger = LoggerFactory.getLogger(LocalAssetTracker.class);

    private boolean generated = false;

    @Override
    public List<Asset> findPendingAssets() {
        if (!generated) {
            logger.info("fetch pending assets");
            generated = true;
            return IntStream.range(0, 15).mapToObj(i -> {
                val id = UUID.randomUUID().toString();
                return new Asset(
                        id,
                        "http://78.media.tumblr.com/tumblr_ll313eVnI91qjahcpo1_1280.jpg",
                        "http://thecatapi.com/?id=3hn",
                        "Asset number " + i,
                        AssetState.PENDING,
                        i % 2 == 0,
                        "user-id-" + (i % 10),
                        LocalDate.now()
                );
            }).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public void updateAssetState(String assetId, AssetState state) throws AssetTrackerException {

    }

}
