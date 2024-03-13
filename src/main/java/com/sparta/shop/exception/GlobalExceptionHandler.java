package com.sparta.shop.exception;


import com.sparta.shop.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j(topic = "GlobalExceptionHandler 예외 전역 처리")
@RestControllerAdvice
public class GlobalExceptionHandler {

    // bean validation exception
    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.info("MethodArgumentNotValidException = {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.createFail(ex.getBindingResult()));
    }

    // 권한 없음
    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedExceptions(AccessDeniedException ex) {
        log.info("AccessDeniedException = {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.createError(ex.getMessage()));
    }

    // 리소스 없음
    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> handleNoSuchElementExceptions(NoSuchElementException ex) {
        log.info("NoSuchElementException = {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.createError(ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> handleIllegalArgumentExceptions(IllegalArgumentException ex) {
        log.info("IllegalArgumentExceptionHandler = {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.createError(ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> handleExceptions(Exception ex) {
        log.info("Exception = {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.createError("INTERNAL-SERVER-ERROR"));
    }
}