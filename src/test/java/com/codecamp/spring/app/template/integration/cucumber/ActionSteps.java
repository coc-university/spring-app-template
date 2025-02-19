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

    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Wenn("ich authentifiziert bin")
    public void withAuth() {
        testRestTemplate = new TestRestTemplate().withBasicAuth("test", "test");
    }

    @Wenn("ich nicht authentifiziert bin")
    public void withoutAuth() {
        testRestTemplate = new TestRestTemplate();
    }

    @Und("ich den Vertrag abrufe")
    public void sendRequest() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:" + port + "/v1/contract")
                .queryParam("name", "Versicherung ABC")
                .build()
                .toUri();
        World.responseEntity = testRestTemplate.getForEntity(uri, ContractResponse.class);
    }

}
