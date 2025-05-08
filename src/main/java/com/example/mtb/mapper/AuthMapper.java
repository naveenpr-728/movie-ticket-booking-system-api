package com.example.mtb.mapper;

import com.example.mtb.dto.AuthResponse;
import com.example.mtb.entity.UserDetails;
import com.example.mtb.security.jwt.TokenPayLoad;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component

public class AuthMapper {

    public AuthResponse authResponseMapper(UserDetails userDetails, TokenPayLoad access, TokenPayLoad refresh,
                                     String accessToken, String refreshToken) {

        return AuthResponse.builder()
                .userId(userDetails.getUserId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .userRole(userDetails.getUserRole().toString())
                .accessExpiration(access.expiration().toEpochMilli())
                .refreshExpiration(refresh.expiration().toEpochMilli())
                .accessToken(accessToken)
                .refreshTokens(refreshToken)
                .refreshTokens(refreshToken)
                .build();

    }
}
