package com.refinitiv.mockdacs.exception;

import feign.FeignException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class RestApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiErrorException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(ApiErrorException e) {
        log.error("[ERROR] ApiErrorException occur with code {} and message: {}", e.getCode(), e.getMessage(), e);
        ErrorResponse response = ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(e.getStatus()).body(response);
    }
}
