package com.refinitiv.mockdacs.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {

    @JsonProperty("error_code")
    private String code;

    @JsonProperty("error_desc")
    private String message;

    @JsonIgnore
    private Object data;
}
