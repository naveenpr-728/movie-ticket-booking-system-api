package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.TheaterRegistrationRequest;
import com.example.mtb.dto.TheaterResponse;
import com.example.mtb.entity.Theater;
import com.example.mtb.entity.TheaterOwner;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.enums.UserRole;
import com.example.mtb.exceptions.TheaterNotFoundByIdException;
import com.example.mtb.exceptions.UserNotFoundByEmailException;
import com.example.mtb.mapper.TheaterMapper;
import com.example.mtb.repository.TheaterRepository;
import com.example.mtb.repository.UserRepository;
import com.example.mtb.service.TheaterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TheaterServiceImpl implements TheaterService {
    public final TheaterRepository theaterRepository;
    public final UserRepository userRepository;
    public final TheaterMapper theaterMapper;

    @Override
    public TheaterResponse addTheater(String email, TheaterRegistrationRequest theaterRegistrationRequest) {
        if (userRepository.existsByEmail(email) && userRepository.findByEmail(email).getUserRole() == UserRole.THEATER_OWNER) {
            UserDetails user = userRepository.findByEmail(email);
            Theater theater = copy(theaterRegistrationRequest, new Theater(), user);
            return theaterMapper.theaterResponseMapper(theater);
        }
        throw new UserNotFoundByEmailException("No Theater Owner with the provided email is present");
    }

    @Override
    public TheaterResponse findTheater(String theaterId) {
            if(theaterRepository.existsById(theaterId)){
                Theater theater = theaterRepository.findById(theaterId).get();
                return theaterMapper.theaterResponseMapper(theater);
            }
            throw new TheaterNotFoundByIdException("Theater not found by the id");
        }

    private Theater copy(TheaterRegistrationRequest registrationRequest, Theater theater, UserDetails userDetails) {
        theater.setAddress(registrationRequest.address());
        theater.setCity(registrationRequest.city());
        theater.setName(registrationRequest.name());
        theater.setLandmark(registrationRequest.landmark());
        theater.setTheaterOwner((TheaterOwner) userDetails);
        theaterRepository.save(theater);
        return theater;
    }


}

