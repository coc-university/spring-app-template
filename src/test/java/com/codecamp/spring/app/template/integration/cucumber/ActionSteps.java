package com.codecamp.spring.app.template.integration.cucumber;

import com.codecamp.spring.app.template.api.model.ContractResponse;
import io.cucumber.java.de.Und;
import io.cucumber.java.de.Wenn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
public class ActionSteps {

    @LocalServerPort
    private int port;


    @Wenn("ich den Vertrag abrufe")
    public void sendRequest() {

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        if (World.isAuthenticated) {
            testRestTemplate = testRestTemplate.withBasicAuth("test", "test");
        }

        World.responseEntity = testRestTemplate.getForEntity(uri(), ContractResponse.class);
    }

    private URI uri() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:" + port + World.endpoint)
                .queryParam("name", "Versicherung ABC")
                .build()
                .toUri();
        log.info("URI: {}", uri);
        return uri;
    }

}
