package com.example.mtb.controller;

import com.example.mtb.dto.TheaterRegistrationRequest;
import com.example.mtb.dto.TheaterRequest;
import com.example.mtb.dto.TheaterResponse;
import com.example.mtb.service.TheaterService;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;
    private final RestResponseBuilder responseBuilder;


    @PostMapping("/theaters")
    public ResponseEntity<ResponseStructure<TheaterResponse>> addTheater(String email, @Valid @RequestBody  TheaterRegistrationRequest theaterRegistrationRequest){
        TheaterResponse theaterResponse = theaterService.addTheater(email, theaterRegistrationRequest);
        return responseBuilder.sucess(HttpStatus.OK, "Theater has been successfully created", theaterResponse);
    }


    @GetMapping("theaters/{theaterId}")
    public ResponseEntity<ResponseStructure<TheaterResponse>> findTheater(@PathVariable String theaterId){
        TheaterResponse theaterResponse = theaterService.findTheater(theaterId);
        return responseBuilder.sucess(HttpStatus.OK, "Theater has been successfully fetched", theaterResponse);

    }

    @PutMapping("/theaters/{theaterId}")
    public ResponseEntity<ResponseStructure<TheaterResponse>> updateTheater(@PathVariable String theaterId, @Valid @RequestBody TheaterRequest registrationRequest){
        TheaterResponse theaterResponse = theaterService.updateTheater(theaterId, registrationRequest);
        return responseBuilder.sucess(HttpStatus.OK, "Theater has been sucessfully Updated", theaterResponse);
}
