package com.exercise.stream.exception;

import org.springframework.http.HttpStatus;

public class ApiValidationException extends ApiRuntimeException {

    private static final String DEFAULT_MESSAGE = "validation failed";

    public ApiValidationException() {

        super(DEFAULT_MESSAGE);
    }

    public ApiValidationException(final String message) {

        super(message);
    }

    @Override
    protected HttpStatus getExceptionResponseStatus() {

        return HttpStatus.BAD_REQUEST;
    }
}

