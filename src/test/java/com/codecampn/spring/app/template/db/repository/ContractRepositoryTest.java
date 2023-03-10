package com.codecampn.spring.app.template.db.repository;

import com.codecampn.spring.app.template.db.model.Contract;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // slice test
class ContractRepositoryTest {

    @Autowired
    private ContractRepository contractRepository;

    @Test
    void shouldReturnContractFromDatabase() {
        // arrange: h2-db has one contract entry by default after startup via liquibase
        String contractName = "Versicherung ABC";
        // act
        Optional<Contract> contract = contractRepository.findContractByName(contractName);
        // assert
        assertThat(contract).isNotEmpty();
    }
}
