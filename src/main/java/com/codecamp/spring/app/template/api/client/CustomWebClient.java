package com.codecamp.spring.app.template.api.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CustomWebClient {

    @Bean(name = "custom-webclient")
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                // add options here
                .baseUrl("http://somewhere")
                .build();
    }
}
