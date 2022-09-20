package com.refinitiv.mockdacs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PoBadRequestException extends ApiErrorException {

    public PoBadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, "4003", message, null);
    }
}
