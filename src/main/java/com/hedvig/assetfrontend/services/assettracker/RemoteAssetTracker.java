package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.domain.Asset;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({"staging", "production"})
public class RemoteAssetTracker implements AssetTracker {

    @Override
    public List<Asset> fetch() {
        throw new RuntimeException("Not implemented yet!");
    }

}
