package com.candidaterecruitment.recruitment.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request ->
                        request.requestMatchers(HttpMethod.POST,"/api/auth/candidate/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/auth/candidate/**").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/api/auth/candidate/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/api/auth/candidate/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/api/auth/recruiter/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/auth/recruiter/**").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/api/auth/recruiter/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE,"/api/auth/recruiter/**").permitAll()
                .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return  httpSecurity.build();
    }
}
