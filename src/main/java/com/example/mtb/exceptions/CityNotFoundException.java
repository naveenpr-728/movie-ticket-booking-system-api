package com.example.mtb.exceptions;

import lombok.Getter;

@Getter
public class CityNotFoundException extends RuntimeException {
    private String message;
    public CityNotFoundException(String message) {
        this.message=message;

    }
}
