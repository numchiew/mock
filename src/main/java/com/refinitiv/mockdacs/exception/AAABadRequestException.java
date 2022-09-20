package com.refinitiv.mockdacs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AAABadRequestException extends ApiErrorException {

    public AAABadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, "4003", message, null);
    }
}
