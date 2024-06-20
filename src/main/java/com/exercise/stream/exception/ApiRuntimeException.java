package com.exercise.stream.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiRuntimeException extends RuntimeException {

    protected ApiRuntimeException() {

        super();
    }

    protected ApiRuntimeException(final String message) {

        super(message);
    }

    protected ApiRuntimeException(final String message, final Throwable t) {

        super(message, t);
    }

    protected abstract HttpStatus getExceptionResponseStatus();
}