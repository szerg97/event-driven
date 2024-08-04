package com.eventdriven.publisherservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class PublisherServiceException extends RuntimeException {

    private String message;
    private String details;
    private HttpStatus httpStatus;

    public static PublisherServiceException sqlException(RuntimeException e) {
        return new PublisherServiceException("SQL exception", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public static PublisherServiceException invalidRequest(String message) {
        return new PublisherServiceException("SQL exception", message, HttpStatus.BAD_REQUEST);
    }
}
