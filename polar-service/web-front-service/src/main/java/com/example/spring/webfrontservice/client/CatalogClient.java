package com.example.spring.webfrontservice.client;

import com.example.spring.webfrontservice.dto.CreateCatalogRequestDTO;
import com.example.spring.webfrontservice.dto.CreateCatalogResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "catalogClient", url = "${polar.edge-service-url}/books")
public interface CatalogClient {

    @PostMapping
    CreateCatalogResponseDTO createCatalog(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody CreateCatalogRequestDTO createCatalogRequestDTO
    );

}
