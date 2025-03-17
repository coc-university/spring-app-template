package com.codecamp.spring.app.template.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
public class RequestFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        // you can access parts of the request here, for example the authentication-principal
        return exchange
                .getPrincipal()
                .flatMap(principal -> {
                    log.info("Principal: {}", principal);
                    return chain.filter(exchange);
                });
    }
}
