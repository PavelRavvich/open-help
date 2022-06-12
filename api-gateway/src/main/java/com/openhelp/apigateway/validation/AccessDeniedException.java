package com.openhelp.apigateway.validation;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * @author Pavel Ravvich.
 */
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(@NotNull String pattern) {
        super(String.format("Access denied no accesses for %s", pattern.toUpperCase(Locale.ROOT)));
    }
}
