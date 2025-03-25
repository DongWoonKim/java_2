package com.example.spring.edgeservice.config.filter;

import com.example.spring.edgeservice.config.client.AuthServiceClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@Order(0)
@Component
public class PreGatewayFilter extends AbstractGatewayFilterFactory<PreGatewayFilter.Config> {

    private final AuthServiceClient authServiceClient;

    public PreGatewayFilter(AuthServiceClient authServiceClient) {
        super(Config.class);
        this.authServiceClient = authServiceClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION);
            log.info("token: {}", token);

            if (token != null && !token.startsWith(config.getTokenPrefix())) {
                exchange.getResponse().setStatusCode(UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return authServiceClient.validToken(token)
                    .flatMap(statusNum -> {
                        if (statusNum == 2) {
                            exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(config.getAuthenticationTimeoutCode()));
                            return exchange.getResponse().setComplete();
                        } else if (statusNum == 3 || statusNum == -1) {
                            exchange.getResponse().setStatusCode(INTERNAL_SERVER_ERROR);
                            return exchange.getResponse().setComplete();
                        }

                        return chain.filter(exchange);
                    })
                    .onErrorResume(e -> {
                        log.error("token filter error: {}", e.getMessage());
                        exchange.getResponse().setStatusCode(INTERNAL_SERVER_ERROR);
                        return exchange.getResponse().setComplete();
                    });
        };
    }

    @Getter
    @Setter
    public static class Config {
        private String tokenPrefix = "Bearer ";
        private int authenticationTimeoutCode = 419;
    }
}
