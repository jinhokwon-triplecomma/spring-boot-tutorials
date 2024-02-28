package com.example.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {MaxUploadSizeExceededException.class})
    public ResponseEntity<Map<String, String>> maxUploadSizeExceededException(MaxUploadSizeExceededException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(
                Collections.singletonMap("response", e.getMessage())
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> runtimeException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("response", ex.getMessage()));
    }
}
