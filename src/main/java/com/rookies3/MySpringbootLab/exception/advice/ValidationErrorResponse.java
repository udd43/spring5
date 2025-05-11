package com.rookies3.myspringbootlab.exception.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ValidationErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String,String> errors;  // ← 네 번째 인자
}
