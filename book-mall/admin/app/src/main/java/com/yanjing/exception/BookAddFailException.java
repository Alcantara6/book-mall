package com.yanjing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookAddFailException extends RuntimeException {
    public BookAddFailException(String message) {
        super(message);
    }
}
