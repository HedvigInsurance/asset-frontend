package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.constant.AssetState;
import com.hedvig.assetfrontend.domain.Asset;

import java.util.List;

public class RemoteAssetTracker implements AssetTracker {

    @Override
    public List<Asset> findPendingAssets() {
        throw new RuntimeException("Not implemented yet!");
    }

    @Override
    public boolean updateAssetState(String id, AssetState state) {
        throw new RuntimeException("Not implemented yet!");
    }

}
