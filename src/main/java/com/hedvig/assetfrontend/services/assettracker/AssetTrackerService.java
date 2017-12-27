package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.web.dto.AssetDTO;
import com.hedvig.common.constant.AssetState;

import java.util.List;

public interface AssetTrackerService {

    void loadPendingAssetsFromTracker();
    List<AssetDTO> findAll();
    AssetDTO find(String assetId) throws AssetNotFoundException;
    void changeAssetState(String assetId, AssetState state) throws AssetTrackerException;

}
