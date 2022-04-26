package com.example.s3spring.services.exceptions;

public class FileException extends RuntimeException{
    public FileException(String message) {
        super(message);
    }
}
