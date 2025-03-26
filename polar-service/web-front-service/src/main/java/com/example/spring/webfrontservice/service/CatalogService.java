package com.example.spring.webfrontservice.service;

import com.example.spring.webfrontservice.client.CatalogClient;
import com.example.spring.webfrontservice.dto.CreateCatalogRequestDTO;
import com.example.spring.webfrontservice.dto.CreateCatalogResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogClient catalogClient;

    public CreateCatalogResponseDTO createCatalog(
            String accessToken,
            CreateCatalogRequestDTO createCatalogRequestDTO
    ) {
        return catalogClient.createCatalog(accessToken, createCatalogRequestDTO);
    }

}
