package com.codecampn.spring.app.template.integration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringAppTemplateTest {

    // start server on random port and load whole application context
    // h2-db has one contract entry by default after startup via liquibase

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void shouldReturnContractResponse() {

        // you can also use TestRestTemplate from spring instead of RestAssured

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .auth().preemptive().basic("test", "test")
                .queryParam("name", "Versicherung ABC")

                .when()
                .get("/v1/contract")


                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .body("title", is("Versicherung ABC"));
    }
}