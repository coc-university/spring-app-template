package com.codecamp.spring.app.template.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@EnableWebFluxSecurity
@Configuration
@Profile({"dev"})
public class SecurityConfig {

    // simple config that uses basic-auth and form-login (username and password in application.yaml)
    // for oauth with jwt, add extra dependency in pom and extend this config

    @Bean
    SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .authorizeExchange(exchange -> exchange

                        // permit non-business endpoints
                        .pathMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/docs-ui/**",
                                "/docs-api/**",
                                "/info",
                                "/health",
                                "/hello"
                        ).permitAll()

                        // use form login for business endpoints
                        .pathMatchers("/v1/**").authenticated()

                        // secure other endpoints by default
                        .anyExchange().denyAll()
                )
                .build();
    }
}

