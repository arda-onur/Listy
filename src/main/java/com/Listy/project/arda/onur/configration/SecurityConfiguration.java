package com.Listy.project.arda.onur.configration;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration  {

    private final JwtAuthenticationConfiguration authenticationConfiguration;
    private final AuthenticationProvider authenticationProvider;


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

           return http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                    authorizationManagerRequestMatcherRegistry
                            .requestMatchers("/auth/**","/signup","/login","/jwt/**",
                                    "/loginpage.css","/index.css").permitAll()
                            .anyRequest().authenticated())
                   .cors(AbstractHttpConfigurer::disable)
                    .csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(authenticationConfiguration, UsernamePasswordAuthenticationFilter.class)

                   .formLogin(httpSecurityFormLoginConfigurer ->
                           httpSecurityFormLoginConfigurer
                                   .loginPage("/login"))

                   .build();
        }



}
