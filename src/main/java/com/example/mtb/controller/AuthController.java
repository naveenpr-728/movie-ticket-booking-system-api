package com.example.mtb.controller;

import com.example.mtb.dto.AuthResponse;
import com.example.mtb.dto.LoginRequest;
import com.example.mtb.security.jwt.AuthenticatedTokenDetails;
import com.example.mtb.service.AuthService;
import com.example.mtb.utility.ResponseStructure;
import com.example.mtb.utility.RestResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<AuthResponse>> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse token = authService.login(loginRequest);
        return responseBuilder.sucess(HttpStatus.OK, "login successful", token);

    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseStructure<AuthResponse>> refresh(HttpServletRequest request){
        AuthenticatedTokenDetails details = (AuthenticatedTokenDetails)request.getAttribute("tokenDetails");
        log.debug("Authenticated token details, email:{}, role:{}", details.email(), details.role());
        AuthResponse authResponse = authService.refresh(details);
        return responseBuilder.sucess(HttpStatus.OK,"login successful",authResponse);

    }
}