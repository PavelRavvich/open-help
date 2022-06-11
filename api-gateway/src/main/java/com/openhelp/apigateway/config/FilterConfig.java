package com.openhelp.apigateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openhelp.apigateway.dto.AccessRequestDto;
import com.openhelp.apigateway.dto.AccessResponseDto;
import com.openhelp.apigateway.enums.EntityType;
import lombok.SneakyThrows;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

/**
 * @author Pavel Ravvich.
 */
@Configuration
public class FilterConfig {

    private final Map<String, EntityType> urlPatternToEntity = Map.of(
            "users", EntityType.USER,
            "roles", EntityType.ROLE,
            "sos", EntityType.SOS,
            "stories", EntityType.STORY,
            "groups", EntityType.GROUP);

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public GlobalFilter customGlobalFilter() {
        return (exchange, chain) -> {
            for (String pattern : urlPatternToEntity.keySet()) {
                if (exchange.getRequest().getURI().getPath().contains(pattern)) {
                    return filter(pattern, chain, exchange);
                }
            }
            return chain.filter(exchange);
        };
    }

    private Mono<Void> filter(String pattern,
                              GatewayFilterChain chain,
                              ServerWebExchange exchange) {
        Mono<AccessRequestDto> body = Mono.just(AccessRequestDto.builder()
                .entityType(urlPatternToEntity.get(pattern)).build());
        return loadBalancedWebClientBuilder().build()
                .post().uri("http://profile/accesses")
                .body((body), new ParameterizedTypeReference<>() {})
                .header(AUTHORIZATION, getHeader(AUTHORIZATION, exchange))
                .header(CONTENT_TYPE, getHeader(CONTENT_TYPE, exchange))
                .retrieve().bodyToMono(AccessResponseDto.class)
                .timeout(Duration.ofMillis(5000))
                .map(response -> {
                    if (response.getOperations().isEmpty()) {
                        throw new RuntimeException("Access denied");
                    }
                    ServerHttpRequest request = exchange.getRequest()
                            .mutate()
                            .header(ACCESS_CONTROL_ALLOW_CREDENTIALS, toHeader(response))
                            .build();
                    return exchange.mutate().request(request).build();
                }).flatMap(chain::filter);
    }

    @SneakyThrows
    String toHeader(AccessResponseDto response) {
        return new ObjectMapper().writeValueAsString(response);
    }

    private String getHeader(String headerName, ServerWebExchange exchange) {
        List<String> headers = exchange.getRequest().getHeaders().get(headerName);
        if (Objects.isNull(headers) || headers.isEmpty()) {
            throw new RuntimeException(
                    String.format("Bad request header %s required", headerName)
            );
        }
        return headers.get(0);
    }
}
