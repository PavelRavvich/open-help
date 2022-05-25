package com.openhelp.profile.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.exception.ConstraintViolationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * @author Pavel Ravvich.
 */
@ControllerAdvice
public class ErrorHandlingController {

    private final static String NOT_FOUND_MSG = "Not Found";
    private final static String NOT_VALID_PASS_MSG = "Invalid password";
    private final static String USR_NOT_FOUND_MSG = "User not found";
    private final static String NOT_UNIQUE_MSG = "Not unique value";

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({
            AccessDeniedException.class,
            NoSuchElementException.class,
            EntityNotFoundException.class,
            EmptyResultDataAccessException.class,
    })
    ErrorResponse onNoSearchException() {
        ErrorResponse error = new ErrorResponse();
        error.setErrors(Collections.singletonList(new Violation("id", NOT_FOUND_MSG)));
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
                    .map(name -> new Violation(name, NOT_UNIQUE_MSG))
                    .collect(Collectors.toList());

            error.setErrors(violations);
        }
        return error;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({
            AuthException.class,
            InternalAuthenticationServiceException.class,
            BadCredentialsException.class,
            DisabledException.class,
            LockedException.class
    })
    ErrorResponse onAuthorizationValidationException() {
        ErrorResponse error = new ErrorResponse();
        List<Violation> violations = new ArrayList<>();
        violations.add(new Violation("username", USR_NOT_FOUND_MSG));
        violations.add(new Violation("password", USR_NOT_FOUND_MSG));
        error.setErrors(violations);
        return error;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(PasswordChangeException.class)
    ErrorResponse onPasswordChangeValidationException() {
        ErrorResponse error = new ErrorResponse();
        List<Violation> violations = new ArrayList<>();
        violations.add(new Violation("oldPassword", NOT_VALID_PASS_MSG));
        error.setErrors(violations);
        return error;
    }

    @Data
    class ErrorResponse {

        private final boolean invalid = true;

        private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        private String error = "Bad Request";

        private List<Violation> errors = new ArrayList<>();

    }

    @Data
    @AllArgsConstructor
    class Violation {

        private String field;

        private String defaultMessage;

    }
}
