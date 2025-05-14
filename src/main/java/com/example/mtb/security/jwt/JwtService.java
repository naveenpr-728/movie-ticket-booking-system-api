package com.example.mtb.security.jwt;

import com.example.mtb.config.AppEnv;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Slf4j
@AllArgsConstructor
@Service
public class JwtService {

    //   private String secret = "p7ctKoWhIiGRHpyZ+t/YQsb0fXdczx6K49QqClbb0yk=\n";

    private final AppEnv appEnv;

    public String createJwtToken(TokenPayLoad tokenPayload) {
        return Jwts.builder()
                .setHeaderParam("type", tokenPayload.tokenType().name().toLowerCase())
                .setClaims(tokenPayload.claims())
                .setSubject(tokenPayload.subject())
                .setIssuedAt(new Date(tokenPayload.issuedAt().toEpochMilli()))
                .setExpiration(new Date(tokenPayload.expiration().toEpochMilli()))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public ExtractToken parseToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token);

            JwsHeader header = claimsJws.getHeader();
            log.info("Header fetched");
            Claims claimBody = claimsJws.getBody();

            ExtractToken extractedToken = new ExtractToken(header, claimBody);
            log.info("token fetched");

            return extractedToken;
        } catch (JwtException e) {
            log.warn("Exception", e);
            return null;
        }
    }

    private Key getSignatureKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(appEnv.getToken().getSecret()));
    }
}
