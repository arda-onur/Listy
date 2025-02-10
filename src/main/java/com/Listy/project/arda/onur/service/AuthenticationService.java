package com.Listy.project.arda.onur.service;

import com.Listy.project.arda.onur.dto.AuthenticationRequestDto;
import com.Listy.project.arda.onur.dto.RegisterRequestDto;
import com.Listy.project.arda.onur.exception.UserAlreadyExistsException;
import com.Listy.project.arda.onur.response.AuthenticationResponse;
import com.Listy.project.arda.onur.constant.Role;
import com.Listy.project.arda.onur.model.User;
import com.Listy.project.arda.onur.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
private final JwtService jwtService;
private final AuthenticationManager authenticationManager;
private final CookieService cookieService;


    public AuthenticationResponse register(RegisterRequestDto request) {
        Objects.requireNonNull(request, "Argument 'request' must not be null");

            log.info("Registering user: {}", request.username());

        this.userRepository.findByUsername(request.username())
                .ifPresent(user ->{
                    log.error("User {} already exists", request.username());
                    throw new UserAlreadyExistsException(user.getUsername());
                });

        var user = User.builder()
                .username(request.username().toLowerCase())
                .password(this.passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

           this.userRepository.save(user);

        return AuthenticationResponse.builder()
                .build();
    }

    public AuthenticationResponse authenticate (AuthenticationRequestDto request, HttpServletResponse response) {
        Objects.requireNonNull(request, "Argument 'request' must not be null");

        log.info("Authenticating user: {}", request.username());

        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),request.password())
        );

        String token = jwtService.generateToken(request).token();

        cookieService.setCookie(token, response);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }




}
