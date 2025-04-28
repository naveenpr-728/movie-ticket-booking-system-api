package com.example.mtb.service;

import com.example.mtb.dto.TheaterRegistrationRequest;
import com.example.mtb.dto.TheaterRequest;
import com.example.mtb.dto.TheaterResponse;

public interface TheaterService {

    TheaterResponse addTheater(String email, TheaterRegistrationRequest theaterRegistrationRequest);


    TheaterResponse findTheater(String theaterId);

    TheaterResponse updateTheater(String theaterId, TheaterRequest registrationRequest);
}

