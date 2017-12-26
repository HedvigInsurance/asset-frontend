package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.constant.AssetState;
import com.hedvig.assetfrontend.domain.Asset;

import java.util.List;

public interface AssetTracker {

    List<Asset> findPendingAssets();
    void updateAssetState(String assetId, AssetState state) throws AssetTrackerException;

}
