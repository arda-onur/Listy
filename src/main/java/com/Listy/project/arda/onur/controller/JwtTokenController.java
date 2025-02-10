package com.Listy.project.arda.onur.controller;

import com.Listy.project.arda.onur.mapper.AuthenticationMapper;
import com.Listy.project.arda.onur.request.AuthenticationRequest;
import com.Listy.project.arda.onur.response.AuthenticationResponse;
import com.Listy.project.arda.onur.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
public class JwtTokenController {


    private final JwtService jwtService;
    private final AuthenticationMapper authenticationMapper;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> sendToken(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(jwtService.generateToken(authenticationMapper.map(authenticationRequest)));
    }
}
