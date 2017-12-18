package com.hedvig.assetfrontend.web;

import com.hedvig.assetfrontend.services.assettracker.AssetTracker;
import com.hedvig.assetfrontend.web.dto.AssetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetTracker assetTracker;

    @Autowired
    public AssetController(AssetTracker assetTracker) {
        this.assetTracker = assetTracker;
    }

    @GetMapping("")
    @Transactional
    public Iterator<AssetDTO> findAll() {
        List<AssetDTO> list = assetTracker.fetch()
                .stream()
                .map(AssetDTO::fromDomain)
                .collect(Collectors.toList());

        return list.iterator();
    }
}

