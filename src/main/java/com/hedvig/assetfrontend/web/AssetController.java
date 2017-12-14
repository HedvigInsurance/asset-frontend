package com.hedvig.assetfrontend.web;

import com.hedvig.assetfrontend.repository.AssetRepository;
import com.hedvig.assetfrontend.web.dto.AssetDTO;
import lombok.val;
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
    private final AssetRepository assetRepository;

    public AssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @GetMapping("")
    @Transactional
    public Iterator<AssetDTO> findAll() {
        try (val stream = assetRepository.streamAll()) {
            List<AssetDTO> list = stream
                    .map(AssetDTO::fromDomain)
                    .collect(Collectors.toList());
            return list.iterator();
        }
    }
}
