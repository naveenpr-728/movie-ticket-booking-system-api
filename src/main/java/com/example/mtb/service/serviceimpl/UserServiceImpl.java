package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.UserRegistrationRequest;
import com.example.mtb.entity.TheaterOwner;
import com.example.mtb.entity.User;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.exceptions.UserExistByEmailException;
import com.example.mtb.repository.UserRepository;
import com.example.mtb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserDetails addUser(UserRegistrationRequest user) {
            if (userRepository.existsByEmail(user.email()))
                throw new UserExistByEmailException("User with the Email is already exists");
//            return copy(user);
            UserDetails userDetails= switch (user.userRole()) {
                case USER -> copy(new User(),user);
                case THEATER_OWNER -> copy(new TheaterOwner(), user);
            };
            System.out.println(user);
            return userDetails;

        }

    private UserDetails copy(UserDetails userRole, UserRegistrationRequest user) {
            userRole.setUserRole(user.userRole());
            userRole.setEmail(user.email());
            userRole.setPassword(user.password());
            userRole.setUsername(user.username());
            userRole.setPhoneNumber(user.phoneNumber());
            userRole.setDateOfBirth(user.DateOfBirth());
            userRepository.save(userRole);
            return userRole;
        }
    }
