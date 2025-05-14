package com.example.mtb.security;

import com.example.mtb.enums.TokenType;
import com.example.mtb.security.filter.AuthFilter;
import com.example.mtb.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
@Slf4j
public class SecurityConfig {

    private final JwtService jwtService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    @Order(2)
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/**");
        http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/register", "/login")
                .permitAll()
                .anyRequest()
                .authenticated());

        setdeafult(new AuthFilter(jwtService, TokenType.ACCESS), http);

        return http.build();
    }

    @Bean
    @Order(1)
    SecurityFilterChain refreshFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable());

        httpSecurity.securityMatcher("/refresh/**");
        httpSecurity.authorizeHttpRequests(auth -> auth
                .anyRequest()
                .authenticated());

        setdeafult(new AuthFilter(jwtService, TokenType.REFRESH), httpSecurity);

        return httpSecurity.build();
    }

    private HttpSecurity setdeafult(AuthFilter authFilter, HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity;

    }

}



