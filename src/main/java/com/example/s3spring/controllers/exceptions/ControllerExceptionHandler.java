package com.example.s3spring.controllers.exceptions;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.example.s3spring.services.exceptions.FileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> fileException(
            FileException e, HttpServletRequest request
    ) {
        StandardError error = new StandardError(
                now(), BAD_REQUEST.value(), "File exception", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonServiceException(
            AmazonServiceException e, HttpServletRequest request
    ) {
        var statusCode = HttpStatus.valueOf(e.getErrorCode());
        StandardError error = new StandardError(
                now(), statusCode.value(), "Amazon service error", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(statusCode).body(error);
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3Exception(
            AmazonS3Exception e, HttpServletRequest request
    ) {
        StandardError error = new StandardError(
                now(), BAD_REQUEST.value(), "Amazon S3 exception", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClientException(
            AmazonClientException e, HttpServletRequest request
    ) {
        StandardError error = new StandardError(
                now(), BAD_REQUEST.value(), "Amazon client error", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

}
