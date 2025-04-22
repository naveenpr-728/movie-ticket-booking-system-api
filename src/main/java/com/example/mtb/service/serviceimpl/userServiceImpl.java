package com.example.mtb.service.serviceimpl;

import com.example.mtb.entity.TheaterOwner;
import com.example.mtb.entity.User;
import com.example.mtb.entity.userDetails;
import com.example.mtb.exceptions.UserExistByEmailException;
import com.example.mtb.repository.userRepository;
import com.example.mtb.service.userService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class userServiceImpl implements userService {

    private final userRepository userRepository;

    @Override
    public userDetails addUser(userDetails userDetails) {
        boolean exists = userRepository.existsByEmail(userDetails.getEmail());
        if (exists) {
            throw new UserExistByEmailException("User with email is already existed");
        }
        userDetails newUser;

        switch (userDetails.getUserRole()) {
            case USER -> newUser = new User();
            case THEATER_OWNER -> newUser = new TheaterOwner();
            default -> throw new IllegalArgumentException("unsupported role: " + userDetails.getUserRole());
        }
        return copyUserDetails(newUser, userDetails);
    }


    private userDetails copyUserDetails(userDetails user, userDetails source) {

        user.setUserRole(source.getUserRole());
        user.setEmail(source.getEmail());
        user.setPassword(source.getPassword());
        user.setCreatedAt(source.getCreatedAt());
        user.setDateOfBirth(source.getDateOfBirth());
        user.setPhoneNumber(source.getPhoneNumber());
        user.setUsername(source.getUsername());
        user.setUpdatedAt(source.getUpdatedAt());

        return userRepository.save(user);
    }
}
