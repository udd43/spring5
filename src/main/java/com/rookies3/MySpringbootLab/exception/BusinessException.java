// src/main/java/com/rookies3/MySpringbootLab/exception/BusinessException.java
package com.rookies3.MySpringbootLab.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {
    private final HttpStatus httpStatus;

    public BusinessException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
