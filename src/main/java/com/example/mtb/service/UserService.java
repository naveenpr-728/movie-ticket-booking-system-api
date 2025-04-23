package com.example.mtb.service;

import com.example.mtb.dto.UserRegistrationRequest;
import com.example.mtb.dto.UserResponse;
import com.example.mtb.dto.UserUpdationRequest;
import com.example.mtb.entity.UserDetails;

public interface UserService {

    // public userDetails addUser(userDetails user);
    // public UserDetails addUser(UserRegistrationRequest user);
    public UserResponse addUser(UserRegistrationRequest user);

    UserResponse editUser(UserUpdationRequest user, String email);
}
