package com.example.spring.basicboard.exception;

import com.example.spring.basicboard.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //예외를 전역으로 저리해주는 것
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUserIdException.class) // 특정 예외가 발생했을 때 이를 가로채서 개발자가 원하는 방식대로 응답하도록 제어하는 것
    public ResponseEntity<ErrorResponseDto> handleDuplicateUserIdException(DuplicateUserIdException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponseDto(HttpStatus.CONFLICT.value(), e.getMessage()));
    }
}
