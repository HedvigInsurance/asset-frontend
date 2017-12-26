package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.constant.AssetState;
import com.hedvig.assetfrontend.web.dto.AssetDTO;

import java.util.List;

public interface AssetTrackerService {

    void loadPendingAssetsFromTracker();
    List<AssetDTO> findAll();
    AssetDTO find(String assetId) throws AssetNotFoundException;
    void changeAssetState(String assetId, AssetState state) throws AssetNotFoundException, AssetTrackerException;

}
