package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.constant.AssetState;
import com.hedvig.assetfrontend.domain.Asset;

import java.util.List;

public interface AssetTracker {

    List<Asset> findPendingAssets();
    boolean updateAssetState(String id, AssetState state);

}
