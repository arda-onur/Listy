package com.Listy.project.arda.onur.controller;

import com.Listy.project.arda.onur.mapper.AuthenticationMapper;
import com.Listy.project.arda.onur.mapper.RegisterMapper;
import com.Listy.project.arda.onur.request.AuthenticationRequest;
import com.Listy.project.arda.onur.request.RegisterRequest;
import com.Listy.project.arda.onur.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AuthenticationMapper authenticationMapper;
    private final RegisterMapper registerMapper;

    @PostMapping("register")
    public String register(@Valid @ModelAttribute RegisterRequest request)  {
        authenticationService.register(registerMapper.map(request));

        return "redirect:/login";

    }

    @PostMapping("authentication")
    public String authenticate(@Valid @ModelAttribute AuthenticationRequest request, HttpServletResponse response)  {
       authenticationService.authenticate(authenticationMapper.map(request),response);
       return "redirect:/index";
    }




}
