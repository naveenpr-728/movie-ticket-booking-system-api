package com.example.mtb.utility;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FieldErrorStructure<T> {
    private int statusCode;

    @JsonProperty(namespace = "error-message")
    private String errorMessage;
    T data;
}
