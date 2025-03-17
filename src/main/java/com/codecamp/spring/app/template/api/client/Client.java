package com.codecamp.spring.app.template.api.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class Client {

    private final WebClient webClient;

    public Client(@Qualifier("custom-webclient") WebClient customWebClient) {
        this.webClient = customWebClient;
    }

    public Flux<String> callSomeApi() {
        return webClient
                .get()
                .uri("/some/path")
                .retrieve()
                .bodyToFlux(String.class);
    }
}
