package com.openhelp.story.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openhelp.story.dto.access.UserAccessDto;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author Pavel Ravvich.
 */
public class SecurityUtils {
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
}
