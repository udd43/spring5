// 파일 위치: src/main/java/com/rookies3/MySpringbootLab/exception/advice/DefaultExceptionHandler.java
package com.rookies3.MySpringbootLab.exception.advice;

import com.rookies3.MySpringbootLab.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorResponse body = new ErrorResponse(
                ex.getHttpStatus().value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(ex.getHttpStatus()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, replacement) -> existing
                ));

        ValidationErrorResponse body = new ValidationErrorResponse(
                400,
                "Validation failed",
                LocalDateTime.now(),
                errors
        );
        return ResponseEntity.badRequest().body(body);
    }
}
