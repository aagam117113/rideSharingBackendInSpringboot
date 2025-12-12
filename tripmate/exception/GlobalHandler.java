package com.student.tripmate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(NotFoundEx.class)
    public ResponseEntity<ApiError> notFound(NotFoundEx ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError("NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(BadRequestEx.class)
    public ResponseEntity<ApiError> bad(BadRequestEx ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError("BAD_REQUEST", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex) {
        FieldError fe = ex.getBindingResult().getFieldErrors().get(0);
        return ResponseEntity.badRequest().body(new ApiError("VALIDATION_ERROR", fe.getDefaultMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> other(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError("INTERNAL_ERROR", ex.getMessage() == null ? "unexpected" : ex.getMessage()));
    }
}
