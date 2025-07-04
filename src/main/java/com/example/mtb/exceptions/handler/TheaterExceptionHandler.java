package com.example.mtb.exceptions.handler;

import com.example.mtb.exceptions.CityNotFoundException;
import com.example.mtb.exceptions.TheaterNotFoundByIdException;
import com.example.mtb.utility.ErrorStructure;
import com.example.mtb.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class TheaterExceptionHandler {
    private final RestResponseBuilder responseBuilder;

    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleTheaterNotFoundByIdException(TheaterNotFoundByIdException ex) {
        return responseBuilder.error(HttpStatus.NOT_FOUND, "Theater with the requested ID not found");
    }

    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleCityNotFoundException(CityNotFoundException ex) {
        return responseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
