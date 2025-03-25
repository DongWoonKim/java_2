package com.example.spring.edgeservice.config.client;

import com.example.spring.edgeservice.dto.ValidTokenRequestDTO;
import com.example.spring.edgeservice.dto.ValidTokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthServiceClient {

    private final WebClient authClient;

    // 1. 유효, 2. 무효, -1: 오류
    public Mono<Integer> validToken(String token) {
        token = token.replaceFirst("(?i)^Bearer ", "");
        return authClient.post()
                .uri("/auths/validToken")
                .bodyValue(
                        ValidTokenRequestDTO.builder()
                                .token(token)
                                .build()
                )
                .retrieve()
                .bodyToMono(ValidTokenResponseDTO.class)
                .map(ValidTokenResponseDTO::getStatusNum)
                .onErrorResume(e -> Mono.just(-1));
    }

}
