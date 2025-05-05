package com.example.mtb.exceptions;

import lombok.Getter;

@Getter
public class MovieNotFoundByIdException extends RuntimeException {

    private String message;

    public MovieNotFoundByIdException(String message) {
        this.message = message;
    }
}
