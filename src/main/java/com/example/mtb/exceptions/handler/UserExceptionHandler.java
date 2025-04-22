package com.example.mtb.exceptions.handler;

import com.example.mtb.exceptions.UserExistByEmailException;
import com.example.mtb.utility.ErrorStructure;
import com.example.mtb.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@AllArgsConstructor
public class UserExceptionHandler {

    private final RestResponseBuilder responseBuilder;

    @ExceptionHandler
    public ResponseEntity<ErrorStructure> handleUserExistByEmailException(UserExistByEmailException ex) {
        return responseBuilder.error(HttpStatus.OK, ex.getMessage());
    }
}
