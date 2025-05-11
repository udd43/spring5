// 파일 위치: src/main/java/com/rookies3/MySpringbootLab/exception/advice/ErrorResponse.java
package com.rookies3.MySpringbootLab.exception.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter @AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
