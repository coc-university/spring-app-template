package com.codecamp.spring.app.template.business.service;

import com.codecamp.spring.app.template.api.model.ContractResponse;
import com.codecamp.spring.app.template.business.exception.ContractNotFoundException;
import com.codecamp.spring.app.template.db.model.Contract;
import com.codecamp.spring.app.template.db.repository.ContractRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // no spring boot involved at all
class ContractServiceTest {

    @InjectMocks
    private ContractService contractService;

    @Mock
    private ContractRepository contractRepository;

    @Test
    void shouldReturnContractResponse() {
        // arrange
        when(contractRepository.findContractByName("Test")).thenReturn(Mono.just(new Contract("Test")));
        // act
        Mono<ContractResponse> contractResponseMono = contractService.findContract("Test");
        // assert
        StepVerifier.create(contractResponseMono)
                .expectNextMatches(contractResponse -> contractResponse.getTitle().equals("Test"))
                .verifyComplete();
    }

    @Test
    void shouldThrowExceptionForUnknownContract() {
        // arrange
        when(contractRepository.findContractByName("Unknown")).thenReturn(Mono.empty());
        // act
        Mono<ContractResponse> result = contractService.findContract("Unknown");
        // assert
        StepVerifier.create(result)
                .expectError(ContractNotFoundException.class)
                .verify();
    }

}
