package com.Listy.project.arda.onur.service;


import com.Listy.project.arda.onur.dto.AuthenticationRequestDto;
import com.Listy.project.arda.onur.repository.UserRepository;
import com.Listy.project.arda.onur.response.AuthenticationResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

  private final UserRepository userRepository;

private static final String SECRET_KEY = "AaPrZ1fRfMVxWK/0KZaq6RheyHjoj5kO5yX6c2G5SM1O8QcTf3t6FjR6UD3VOQY6";

public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public AuthenticationResponse generateToken(AuthenticationRequestDto request) {

    var user = this.userRepository.findByUsername(request.username())
            .orElseThrow(() -> {
              log.warn("User not found: {}", request.username());
              return new UsernameNotFoundException("User not found");
            });

  return  AuthenticationResponse.builder()
          .token(generateToken(new HashMap<>(),user))
          .build();
  }


  public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
  return Jwts
          .builder()
          .setClaims(claims)
          .setSubject(userDetails.getUsername())
          .setIssuedAt(new Date(System.currentTimeMillis()))
          .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60  *24))
          .signWith(getSignInKey(), SignatureAlgorithm.HS256)
          .compact();
  }
  public  boolean isTokenValid(String token,UserDetails userDetails) {
   final String username = extractUsername(token);
   return (username.equals(userDetails.getUsername())) &&  !isTokenExpired(token);
  }

  public boolean isTokenExpired(String token) {
  return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
  return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
