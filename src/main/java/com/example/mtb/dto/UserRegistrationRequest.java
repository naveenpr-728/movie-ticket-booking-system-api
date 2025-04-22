package com.example.mtb.dto;

import com.example.mtb.enums.UserRole;

import java.time.LocalDate;

public record UserRegistrationRequest (
        String email,
        String password,
        String username,
        String phoneNumber,
        UserRole userRole,
        LocalDate DateOfBirth
        )
{ }
