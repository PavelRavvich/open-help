package com.openhelp.story.controller;

import com.openhelp.story.validation.NoSuchStoryException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.exception.ConstraintViolationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Pavel Ravvich.
 */
@ControllerAdvice
public class ErrorHandlerController {

    private final static String NO_SUCH_STORY = "No search story";
    private final static String NO_UNIQUE_MSG = "Not unique";

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({NoSuchStoryException.class})
    public ErrorResponse onBanException() {
        ErrorResponse error = new ErrorResponse();
        error.setErrors(Collections.singletonList(
                new Violation("msg", NO_SUCH_STORY)));
        return error;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    ErrorResponse onConstraintValidationException(@NotNull ConstraintViolationException e) {
        ErrorResponse error = new ErrorResponse();
        if (e.getCause().getMessage().contains("duplicate")) {
            String[] fields = e.getConstraintName()
                    .replace("ui_", "")
                    .replace("uc_", "")
                    .split("_");

            List<Violation> violations = Arrays.stream(fields)
                    .filter(item -> !"".equals(item))
                    .map(name -> new Violation(name, NO_UNIQUE_MSG))
                    .collect(Collectors.toList());

            error.setErrors(violations);
        }
        return error;
    }

    @Data
    static class ErrorResponse {

        private final boolean invalid = true;

        private String error = "Bad Request";

        private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        private List<Violation> errors = new ArrayList<>();
    }

    @Data
    @AllArgsConstructor
    static class Violation {

        private String field;

        private String defaultMessage;
    }
}
