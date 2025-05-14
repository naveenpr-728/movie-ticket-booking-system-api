package com.example.mtb.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;

public record ExtractToken(
        JwsHeader header,
        Claims claims
) {
}
