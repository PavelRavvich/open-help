package com.openhelp.profile.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openhelp.profile.dto.access.AccessResponseDto;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Pavel Ravvich.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("POST");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void addFormatters(@NotNull FormatterRegistry registry) {
        registry.addConverter(new Converter<String, AccessResponseDto>() {
            @SneakyThrows
            @Override
            public AccessResponseDto convert(@NotNull String json) {
                return mapper.readValue(json, AccessResponseDto.class);
            }
        });
    }
}
