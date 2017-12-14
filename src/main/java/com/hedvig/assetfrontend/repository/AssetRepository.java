package com.hedvig.assetfrontend.repository;

import com.hedvig.assetfrontend.domain.Asset;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface AssetRepository extends CrudRepository<Asset, String> {
    @Query("select a from Asset a")
    Stream<Asset> streamAll();
}
