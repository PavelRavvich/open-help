package com.openhelp.apigateway.validation;

import org.jetbrains.annotations.NotNull;

/**
 * @author Pavel Ravvich.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(@NotNull String headerName) {
        super(String.format("Bad request \nHeader: %s required", headerName));
    }
}
