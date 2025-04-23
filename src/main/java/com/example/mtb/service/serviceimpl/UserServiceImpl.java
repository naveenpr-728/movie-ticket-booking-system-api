package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.UserRegistrationRequest;
import com.example.mtb.dto.UserResponse;
import com.example.mtb.dto.UserUpdationRequest;
import com.example.mtb.entity.TheaterOwner;
import com.example.mtb.entity.User;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.exceptions.UserExistByEmailException;
import com.example.mtb.exceptions.UserNotFoundByEmailException;
import com.example.mtb.mapper.UserDetailsMapper;
import com.example.mtb.repository.UserRepository;
import com.example.mtb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsMapper userMapper;

    @Override
    public UserResponse addUser(UserRegistrationRequest user) {
        if (userRepository.existsByEmail(user.email()))
            throw new UserExistByEmailException("User with this Email is already exists");
//            return copy(user);
        UserDetails userDetails = switch (user.userRole()) {
            case USER -> copy(new User(), user);
            case THEATER_OWNER -> copy(new TheaterOwner(), user);
        };
        return userMapper.userDetailsResponseMapper(userDetails);

    }

    @Override
    public UserResponse editUser(UserUpdationRequest userRequest, String email) {
        if (userRepository.existsByEmail(email)){
            UserDetails user = userRepository.findByEmail(email);

            if( userRepository.existsByEmail(userRequest.email()))
                throw new UserExistByEmailException("User with this email already exists");

            user = copy(user, userRequest);

            return userMapper.userDetailsResponseMapper(user);
        }

        throw new UserNotFoundByEmailException("Email not found in the Database");

    }


    private UserDetails copy(UserDetails userRole, UserRegistrationRequest user) {
//        UserDetails userRole = user.getUserRole()==UserRole.USER ? new User() : new TheaterOwner();
        userRole.setUserRole(user.userRole());
        userRole.setEmail(user.email());
        userRole.setPassword(user.password());
        userRole.setDateOfBirth(user.DateOfBirth());
        userRole.setPhoneNumber(user.phoneNumber());
        userRole.setUsername(user.username());
        userRepository.save(userRole);
        return userRole;
    }
    private UserDetails copy(UserDetails userRole, UserUpdationRequest user) {
        userRole.setDateOfBirth(user.DateOfBirth());
        userRole.setPhoneNumber(user.phoneNumber());
        userRole.setEmail(user.email());
        userRole.setUsername(user.username());
        userRepository.save(userRole);
        return userRole;
    }
}