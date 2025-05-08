    package com.example.mtb.controller;

    import com.example.mtb.dto.AuthResponse;
    import com.example.mtb.dto.LoginRequest;
    import com.example.mtb.service.AuthService;
    import com.example.mtb.utility.ResponseStructure;
    import com.example.mtb.utility.RestResponseBuilder;
    import lombok.AllArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;

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
    }
