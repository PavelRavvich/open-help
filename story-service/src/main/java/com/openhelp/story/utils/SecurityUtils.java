package com.openhelp.story.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openhelp.story.enums.EntityType;
import com.openhelp.story.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

/**
 * // TODO make as a @Service bean and mock return.
 * @author Pavel Ravvich.
 */
public class SecurityUtils {

    public static boolean is(EntityType entity, OperationType operation) {
        if (Objects.isNull(entity) || Objects.isNull(operation)) {
            throw new IllegalArgumentException();
        }
        return getUserAccess()
                .getAccesses()
                .stream()
                .anyMatch(item -> entity == item.getEntityType()
                        && operation == item.getOperationType());
    }

    @SneakyThrows
    public static UserAccessDto getUserAccess() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
            throw new RuntimeException();
        }
        String header = ((ServletRequestAttributes) attributes).getRequest()
                .getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);
        if (Objects.isNull(header)) {
            throw new RuntimeException();
        }
        return new ObjectMapper().readValue(header, UserAccessDto.class);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccessDto {

        @JsonProperty("entityType")
        private EntityType entityType;

        @JsonProperty("operationType")
        private OperationType operationType;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserAccessDto {

        @JsonProperty("userId")
        private Long userId;

        @JsonProperty("accesses")
        private List<AccessDto> accesses;
    }
}
