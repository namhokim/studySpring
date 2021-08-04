package com.example.datajpatest.exception;

import lombok.Getter;

@Getter
public abstract class ApiBaseException extends RuntimeException {
    private final ExceptionCode code;
    private final String message;

    public ApiBaseException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
