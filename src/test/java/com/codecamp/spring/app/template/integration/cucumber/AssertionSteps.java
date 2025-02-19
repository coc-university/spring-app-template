package com.codecamp.spring.app.template.integration.cucumber;

import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Und;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AssertionSteps {

    @Dann("ist der Http Status Code {int}")
    public void checkStatusCode(int givenStatusCode) {
        HttpStatusCode responseStatusCode = World.responseEntity.getStatusCode();
        log.info("status code: {}", responseStatusCode);
        assertThat(responseStatusCode).isEqualTo(HttpStatusCode.valueOf(givenStatusCode));
    }

    @Und("der Body ist korrekt")
    public void checkBody() {
        String responseBodyTitle = Objects.requireNonNull(World.responseEntity.getBody()).getTitle();
        log.info("body title: {}", responseBodyTitle);
        assertThat(responseBodyTitle).isEqualTo("Versicherung ABC");
    }

}
