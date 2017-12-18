package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.domain.Asset;

import java.util.List;

public interface AssetTracker {

    List<Asset> fetch();

}
