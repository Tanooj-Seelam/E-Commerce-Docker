package com.example.ecommerce.exceptions;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }
}