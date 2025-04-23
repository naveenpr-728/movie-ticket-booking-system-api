package com.example.mtb.dto;

import com.example.mtb.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UserRegistrationRequest(
        @NotNull
        @Email(message = "Invalid email format") // Improved error message
        String email,
        @NotNull
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
                message = "Password must be at least 8 characters and include one uppercase letter, one lowercase letter, one digit, and one special character")
        String password,
        @NotNull
        @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$", // Added length restriction
                message = "Username must be 3-20 characters and can include letters, numbers, and underscores")
        String username,
        @NotNull
        @Pattern(regexp = "^[6-9]\\d{9}$", // Corrected regex for Indian phone numbers
                message = "Phone number must be a 10-digit number starting with 6, 7, 8, or 9")
        String phoneNumber,
        @NotNull
        UserRole userRole,
        @NotNull
        @Past(message = "Date of birth must be in the past")
        LocalDate DateOfBirth
) {
}
