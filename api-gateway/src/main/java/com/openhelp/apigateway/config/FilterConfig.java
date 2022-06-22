package com.openhelp.apigateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openhelp.apigateway.dto.UserAccessDto;
import com.openhelp.apigateway.validation.BadRequestException;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${endpoint.patterns.permitAll}")
    private String[] permitAllPatterns;

    @Bean
    @LoadBalanced
    public WebClient.Builder clientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public GlobalFilter authFilter() {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            for (String pattern : permitAllPatterns) {
                if (path.contains(pattern)) {
                    return chain.filter(exchange);
                }
            }
            return doFilter(chain, exchange);
        };
    }

    @NotNull
    private Mono<Void> doFilter(@NotNull GatewayFilterChain chain,
                                @NotNull ServerWebExchange exchange) {
        return clientBuilder()
                .build()
                .get()
                .uri(ACCESS_URL)
                .header(AUTHORIZATION, getHeader(AUTHORIZATION, exchange))
                .header(CONTENT_TYPE, getHeader(CONTENT_TYPE, exchange))
                .retrieve().bodyToMono(UserAccessDto.class)
                .timeout(Duration.ofMillis(5000))
                .map(response -> getServerWebExchange(response, exchange))
                .flatMap(chain::filter);
    }

    @NotNull
    private ServerWebExchange getServerWebExchange(@NotNull UserAccessDto accessesResponse,
                                                   @NotNull ServerWebExchange exchange) {
        return exchange
                .mutate()
                .request(exchange
                        .getRequest()
                        .mutate()
                        .header(ACCESS_CONTROL_REQUEST_HEADERS, asString(accessesResponse))
                        .build())
                .build();
    }

    @NotNull
    private String getHeader(@NotNull String headerName, @NotNull ServerWebExchange exchange) {
        List<String> headers = exchange
                .getRequest()
                .getHeaders()
                .get(headerName);
        if (Objects.isNull(headers) || headers.isEmpty()) {
            throw new BadRequestException(headerName);
        }
        return headers.get(0);
    }

    @SneakyThrows
    private String asString(@NotNull UserAccessDto response) {
        return mapper.writeValueAsString(response);
    }
}
