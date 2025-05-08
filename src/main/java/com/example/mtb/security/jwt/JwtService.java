package com.example.mtb.security.jwt;

import com.example.mtb.config.AppEnv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@AllArgsConstructor
@Service
public class JwtService {

 //   private String secret = "p7ctKoWhIiGRHpyZ+t/YQsb0fXdczx6K49QqClbb0yk=\n";

    private final AppEnv appEnv;

    public String createJwtToken(TokenPayLoad tokenPayload) {
        return Jwts.builder()
                .setClaims(tokenPayload.claims())
                .setSubject(tokenPayload.subject())
                .setIssuedAt(new Date(tokenPayload.issuedAt().toEpochMilli()))
                .setExpiration(new Date(tokenPayload.expiration().toEpochMilli()))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignatureKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(appEnv.getToken().getSecret()));
    }
}
