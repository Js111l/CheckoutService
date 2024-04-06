package com.cncservice.ecommerce.exception;

public class LogicalException extends RuntimeException {
    private final ErrorKey errorKey;

    public LogicalException(ErrorKey errorKey) {
        this.errorKey = errorKey;
    }
}