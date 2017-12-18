package com.hedvig.assetfrontend.services.assettracker;

import com.hedvig.assetfrontend.domain.Asset;
import com.hedvig.assetfrontend.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile({"development", "default"})
public class LocalAssetTracker implements AssetTracker {

    private final AssetRepository assetRepository;
    private final DatabaseLoader databaseLoader;

    @Autowired
    public LocalAssetTracker(AssetRepository assetRepository, DatabaseLoader databaseLoader) {
        this.assetRepository = assetRepository;
        this.databaseLoader = databaseLoader;
    }

    @PostConstruct
    public void setup() {
        databaseLoader.loadData();
    }

    @Override
    public List<Asset> fetch() {
        return assetRepository.streamAll().collect(Collectors.toList());
    }
}
