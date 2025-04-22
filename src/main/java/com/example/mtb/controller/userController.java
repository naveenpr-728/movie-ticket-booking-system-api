package com.example.mtb.controller;

import com.example.mtb.entity.userDetails;
import com.example.mtb.service.userService;
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
public class userController {

    private final userService userservice;
    private final RestResponseBuilder ResponseBuilder;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<userDetails>> addUser(@RequestBody userDetails user) {
        userDetails userdetails = userservice.addUser(user);
        return ResponseBuilder.sucess(HttpStatus.CREATED, "New user register successfully ", userdetails);


    }
}
