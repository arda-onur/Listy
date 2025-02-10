package com.Listy.project.arda.onur.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieService {



    public  void setCookie(String jwtToken, HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", jwtToken);

        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(1000 * 60  *24);

        response.addCookie(cookie);
    }
    
}
