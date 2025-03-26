package com.example.spring.webfrontservice.client;

import com.example.spring.webfrontservice.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authClient", url = "${polar.edge-service-url}/auths")
public interface AuthClient {

    @PostMapping("/join")
    JoinClientResponseDTO join(@RequestBody JoinRequestDTO joinRequestDTO);

    @PostMapping("/login")
    LoginClientResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO);

    @PostMapping("/refresh")
    RefreshTokenClientResponseDTO refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO);

}
