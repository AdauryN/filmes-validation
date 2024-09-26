package com.example.fds2project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection entirely (for development purposes)
                .csrf(csrf -> csrf.disable())
                // Allow frames from the same origin (needed for H2 console)
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions
                                .sameOrigin()
                        )
                )
                // Authorize requests
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/register", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                // Use default login form
                .formLogin(form -> form
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Add this bean definition
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
