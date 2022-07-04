package com.openhelp.group.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openhelp.group.enums.EntityType;
import com.openhelp.group.enums.OperationType;
import com.openhelp.group.validation.AccessDeniedException;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

/**
 * @author Pavel Ravvich.
 */
public class SecurityUtils {

    public static void checkReadAccess(@NotNull Long authorId, @NotNull EntityType entity) {
        boolean isOwn = authorId.equals(SecurityUtils.getUserAccess().getUserId());
        boolean isReadOwn = SecurityUtils.is(OperationType.READ_OWN, entity);
        boolean isReadAny = SecurityUtils.is(OperationType.READ_ANY, entity);
        if (!isReadAny && !(isOwn && isReadOwn)) {
            throw new AccessDeniedException();
        }
    }

    public static void checkUpdateAccess(@NotNull Long authorId, @NotNull EntityType entity) {
        boolean isOwn = authorId.equals(SecurityUtils.getUserAccess().getUserId());
        boolean isUpdateAny = SecurityUtils.is(OperationType.UPDATE_ANY, entity);
        boolean isUpdateOwn = SecurityUtils.is(OperationType.UPDATE_OWN, entity);
        if (!isUpdateAny && !(isOwn && isUpdateOwn)) {
            throw new AccessDeniedException();
        }
    }

    public static void checkDeleteAccess(@NotNull Long authorId, @NotNull EntityType entity) {
        boolean isOwn = authorId.equals(SecurityUtils.getUserAccess().getUserId());
        boolean isDeleteAny = SecurityUtils.is(OperationType.DELETE_ANY, entity);
        boolean isDeleteOwn = SecurityUtils.is(OperationType.DELETE_OWN, entity);
        if (!isDeleteAny && !(isOwn && isDeleteOwn)) {
            throw new AccessDeniedException();
        }
    }

    public static boolean is(OperationType operation, EntityType entity) {
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
