package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.constant.AssetState;

public interface AssetTracker {

    void findPendingAssets();
    boolean updateAssetState(String id, AssetState state);

}
