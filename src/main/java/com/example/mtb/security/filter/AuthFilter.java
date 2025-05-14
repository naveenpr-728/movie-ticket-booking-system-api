package com.example.mtb.security.filter;

import com.example.mtb.security.jwt.ExtractToken;
import com.example.mtb.enums.TokenType;
import com.example.mtb.security.jwt.AuthenticatedTokenDetails;
import com.example.mtb.security.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor

public class    AuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final TokenType tokenType;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);


        if (isValid(header)) {

            String token = header.contains("Bearer") ? header.substring(7) : header;
            var extractedToken = jwtService.parseToken(token);

            Claims claims = Optional.ofNullable(extractedToken)
                    .map(ExtractToken::claims)
                    .orElse(null);

            JwsHeader headers = Optional.ofNullable(extractedToken)
                    .map(e -> extractedToken.header())
                    .orElse(null);

            boolean correctedToken = Optional.ofNullable(headers)
                    .map(h -> (String) h.get("type"))
                    .map(String::toUpperCase)
                    .filter(f -> TokenType.valueOf(String.valueOf(f))
                            .equals(tokenType))
                    .map(t -> true)
                    .orElse(false);

            if (!correctedToken || claims == null) filterChain.doFilter(request, response);


            String role = claims.get("role", String.class);
            String email = claims.getSubject();
            log.debug("Token found, extracted claims, email/username; {}, role: {}", email, role);



            if (isValid(email) && isValid(role) && SecurityContextHolder.getContext().getAuthentication() == null) {
                log.info("Auth null");
                var authorities = List.of(new SimpleGrantedAuthority(role));
                var authToken = new UsernamePasswordAuthenticationToken(email, null, authorities);
                authToken.setDetails(request);

                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.info("User '{}' - authenticated.", email);

                AuthenticatedTokenDetails tokenDetails = new AuthenticatedTokenDetails(email, role, claims.getExpiration().toInstant(), token);

                request.setAttribute("tokenDetails", tokenDetails);


            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isValid(String s) {
        return s != null && !s.isBlank();
    }

}
