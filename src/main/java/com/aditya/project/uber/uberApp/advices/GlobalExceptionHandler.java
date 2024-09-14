package com.aditya.project.uber.uberApp.advices;


import com.aditya.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.aditya.project.uber.uberApp.exceptions.RuntimeConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeConflictException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleRuntimeConflictException(RuntimeConflictException e) {
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.CONFLICT)
                .message(e.getMessage())
                .build();
        return buildErreoResponseEntity(apiError);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleResourceNotFoundException(ResourceNotFoundException e) {
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build();
        return buildErreoResponseEntity(apiError);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ApiError>> handleInternalServerError(Exception e) {
        ApiError apiError=ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
        return buildErreoResponseEntity(apiError);
    }

    public ResponseEntity<ApiResponse<ApiError>> buildErreoResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }
}
