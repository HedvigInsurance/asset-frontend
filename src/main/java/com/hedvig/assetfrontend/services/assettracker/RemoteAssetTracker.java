package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.constant.AssetState;

public class RemoteAssetTracker implements AssetTracker {

    @Override
    public void findPendingAssets() {
        throw new RuntimeException("Not implemented yet!");
    }

    @Override
    public boolean updateAssetState(String id, AssetState state) {
        throw new RuntimeException("Not implemented yet!");
    }

}
