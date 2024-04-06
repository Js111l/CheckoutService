package com.cncservice.ecommerce.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

public enum ErrorKey {
    NOT_FOUND(HttpStatus.NOT_FOUND);
    private final HttpStatus httpStatus;

    ErrorKey(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
