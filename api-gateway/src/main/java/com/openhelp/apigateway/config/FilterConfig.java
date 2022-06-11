package com.openhelp.apigateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openhelp.apigateway.dto.AccessesDto;
import com.openhelp.apigateway.enums.EntityType;
import com.openhelp.apigateway.validation.AccessDeniedException;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

/**
 * @author Pavel Ravvich.
 */
@Configuration
public class FilterConfig {

    private final ObjectMapper mapper = new ObjectMapper();

    private final static String ACCESS_URL = "http://profile/accesses";

    private final Map<String, String> patterns = Map.of(
            "sos", EntityType.SOS.getType(),
            "stories", EntityType.STORY.getType(),
            "groups", EntityType.GROUP.getType());

    @Bean
    @LoadBalanced
    public WebClient.Builder clientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public GlobalFilter authFilter() {
        return (exchange, chain) ->
                patterns.keySet()
                        .stream()
                        .filter(pattern -> exchange
                                .getRequest()
                                .getURI()
                                .getPath()
                                .contains(pattern))
                        .findFirst()
                        .map(pattern -> doFilter(pattern, chain, exchange))
                        .orElse(chain.filter(exchange));
    }

    @NotNull
    private Mono<Void> doFilter(@NotNull String pattern,
                                @NotNull GatewayFilterChain chain,
                                @NotNull ServerWebExchange exchange) {
        return clientBuilder()
                .build()
                .get()
                .uri(String.format("%s/%s", ACCESS_URL, patterns.get(pattern)))
                .header(AUTHORIZATION, getHeader(AUTHORIZATION, exchange))
                .header(CONTENT_TYPE, getHeader(CONTENT_TYPE, exchange))
                .retrieve().bodyToMono(AccessesDto.class)
                .timeout(Duration.ofMillis(5000))
                .map(response -> getServerWebExchange(pattern, response, exchange))
                .flatMap(chain::filter);
    }

    @NotNull
    private ServerWebExchange getServerWebExchange(@NotNull String pattern,
                                                   @NotNull AccessesDto accesses,
                                                   @NotNull ServerWebExchange exchange) {
        if (accesses.getOperations().isEmpty()) {
            throw new AccessDeniedException(pattern);
        }
        return exchange
                .mutate()
                .request(exchange
                        .getRequest()
                        .mutate()
                        .header(ACCESS_CONTROL_REQUEST_HEADERS, json(accesses))
                        .build())
                .build();
    }

    @SneakyThrows
    private String json(@NotNull AccessesDto response) {
        return mapper.writeValueAsString(response);
    }

    @NotNull
    private String getHeader(@NotNull String headerName, @NotNull ServerWebExchange exchange) {
        List<String> headers = exchange
                .getRequest()
                .getHeaders()
                .get(headerName);
        if (Objects.isNull(headers) || headers.isEmpty()) {
            throw new RuntimeException(
                    String.format("Bad request header %s required", headerName)
            );
        }
        return headers.get(0);
    }
}
