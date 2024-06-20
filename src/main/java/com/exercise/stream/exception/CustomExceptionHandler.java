package com.exercise.stream.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ApiRuntimeException.class })
    public ResponseEntity<Object> handleApiRuntimeException(Exception exception, WebRequest request) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus httpStatus;
        if (exception instanceof ApiRuntimeException) {
            httpStatus = ((ApiRuntimeException) exception).getExceptionResponseStatus();
        } else {
            throw exception;
        }
        String bodyOfResponse = exception.getMessage();
        return handleExceptionInternal(exception, bodyOfResponse, headers, httpStatus, request);
    }
}

