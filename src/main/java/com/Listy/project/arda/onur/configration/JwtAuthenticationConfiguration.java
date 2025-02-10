package com.Listy.project.arda.onur.configration;

import com.Listy.project.arda.onur.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationConfiguration extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        removeExpiredJwtCookie(request, response);

        String jwtToken = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    jwtToken = cookie.getValue();
                    break;
                }
            }
        }

        if (jwtToken == null || jwtToken.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        final String username = jwtService.extractUsername(jwtToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void removeExpiredJwtCookie(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    boolean tokenExpired = false;
                    try {

                        tokenExpired = this.jwtService.isTokenExpired(token);
                    } catch (ExpiredJwtException e) {

                        tokenExpired = true;
                    }
                    if (token != null && !token.isEmpty() && tokenExpired) {

                        Cookie expiredCookie = new Cookie("jwt", null);
                        expiredCookie.setPath("/");
                        expiredCookie.setMaxAge(0);
                        response.addCookie(expiredCookie);
                        break;
                    }
                }
            }
        }
    }
}
