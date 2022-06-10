package com.openhelp.apigateway.config;

import com.openhelp.apigateway.dto.AccessStatusRequestDto;
import com.openhelp.apigateway.dto.AccessStatusResponseDto;
import com.openhelp.apigateway.enums.AccessStatusType;
import com.openhelp.apigateway.enums.EntityType;
import com.openhelp.apigateway.enums.OperationType;
import org.hibernate.validator.constraints.Mod11Check;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

/**
 * @author Pavel Ravvich.
 */
@Configuration
public class FilterConfig {

    @Bean
    public GlobalFilter customGlobalFilter() {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            if (path.contains("login") || path.contains("registration")) {
                throw new RuntimeException("Access denied");
            }
            AccessStatusRequestDto body = AccessStatusRequestDto.builder()
                    .entityType(EntityType.USER)
                    .operationType(OperationType.READ_ANY)
                    .build();
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String authorization = Objects.requireNonNull(headers.get("Authorization")).get(0);
            String contentType = Objects.requireNonNull(headers.get("Content-Type")).get(0);
            WebClient client = loadBalancedWebClientBuilder().build();
            return client
                    .post()
                    .uri("http://profile/users/accessStatus")
                    .body(Mono.just(body), new ParameterizedTypeReference<>() {})
                    .header("Authorization", authorization)
                    .header("Content-Type", contentType)
                    .retrieve()
                    .bodyToMono(AccessStatusResponseDto.class)
                    .timeout(Duration.ofMillis(5000))
                    .map(accessStatus -> {
                        if (accessStatus.getAccessStatus() != AccessStatusType.OPEN) {
                            throw new RuntimeException("Access denied");
                        }
                        return exchange;
                    }).flatMap(chain::filter);
        };
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

}
