package com.codecampn.spring.app.template.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@EnableWebSecurity
@Configuration
@Profile({"dev"})
public class SecurityConfig {

    // simple config that uses basic-auth and form-login (username and password in application.yaml)
    // for oauth with jwt, add extra dependency in pom and extend this config

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .authorizeHttpRequests(customizer -> customizer

                        // permit non-business endpoints
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/docs-ui/**",
                                "/docs-api/**",
                                "/actuator/**",
                                "/hello"
                        ).permitAll()

                        // use form login for business endpoints
                        .requestMatchers("/v1/**").authenticated()

                        // secure other endpoints by default
                        .anyRequest().denyAll()
                )
                .build();
    }
}

