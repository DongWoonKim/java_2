package com.example.spring.webfrontservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "catalogClient", url = "${polar.edge-service-url}/books")
public interface CatalogClient {
}
