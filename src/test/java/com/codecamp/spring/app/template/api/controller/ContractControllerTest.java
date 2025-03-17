package com.codecamp.spring.app.template.api.controller;

import com.codecamp.spring.app.template.api.model.ContractResponse;
import com.codecamp.spring.app.template.business.service.ContractService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WithMockUser
@WebFluxTest(controllers = ContractController.class) // slice test
class ContractControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private ContractService contractService;

    @Test
    void shouldReturnContractResponse() {
        when(contractService.findContract(any()))
                .thenReturn(Mono.just(new ContractResponse().title("Versicherung ABC")));

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v1/contract")
                                .queryParam("name", "Versicherung ABC")
                                .build())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void shouldReturnContractResponse_v2() {
        when(contractService.findContract(any()))
                .thenReturn(Mono.just(new ContractResponse().title("Versicherung ABC")));

        Mono<ContractResponse> responseMono = contractService.findContract("Versicherung ABC");
        StepVerifier.create(responseMono)
                .expectNextMatches(contractResponse -> contractResponse.getTitle().equals("Versicherung ABC"))
                .verifyComplete();
    }
}
