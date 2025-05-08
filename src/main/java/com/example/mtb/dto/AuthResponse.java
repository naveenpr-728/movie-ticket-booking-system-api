package com.example.mtb.dto;

import lombok.Builder;

@Builder
public record AuthResponse(
        String userId,
        String email,
        String username,
        String userRole,
        Long accessExpiration,
        Long refreshExpiration,
        String accessToken,
        String refreshTokens
) {
}
