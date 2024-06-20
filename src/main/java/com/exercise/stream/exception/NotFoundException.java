package com.exercise.stream.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiRuntimeException {

    private static final String DEFAULT_MESSAGE = "An entity for the specified identifier was not found";

    public NotFoundException() {

        super(DEFAULT_MESSAGE);
    }

    public NotFoundException(final String message) {

        super(message);
    }

    @Override
    public HttpStatus getExceptionResponseStatus() {

        return HttpStatus.NOT_FOUND;
    }
}
