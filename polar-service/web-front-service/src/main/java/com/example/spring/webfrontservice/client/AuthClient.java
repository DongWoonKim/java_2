package com.example.spring.webfrontservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "authClient", url = "${polar.edge-service-url}/auths")
public interface AuthClient {



}
