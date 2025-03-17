package com.codecamp.spring.app.template.db.repository;

import com.codecamp.spring.app.template.db.model.Contract;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest // slice test
class ContractRepositoryTest {

    @Autowired
    private ContractRepository contractRepository;

    @Test
    void shouldReturnContractFromDatabase() {
        // arrange: h2-db has one contract entry by default after startup via liquibase
        String contractName = "Versicherung ABC";
        // act
        Mono<Contract> contractMono = contractRepository.findContractByName(contractName);
        // assert
        StepVerifier.create(contractMono)
                .expectNextMatches(contract -> contract.getName().equals("Versicherung ABC"))
                .verifyComplete();
    }
}
