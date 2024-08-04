package com.eventdriven.publisherservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(PublisherServiceException.class)
    public ResponseEntity<Object> handlePublisherServiceException(PublisherServiceException ex) {
        final ApiError error = new ApiError(ex.getMessage(), ex.getDetails());
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }
}
