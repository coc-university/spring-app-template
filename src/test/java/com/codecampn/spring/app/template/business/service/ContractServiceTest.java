package com.codecampn.spring.app.template.business.service;

import com.codecampn.spring.app.template.api.model.ContractResponse;
import com.codecampn.spring.app.template.business.exception.ContractNotFoundException;
import com.codecampn.spring.app.template.db.model.Contract;
import com.codecampn.spring.app.template.db.repository.ContractRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        when(contractRepository.findContractByName("Test")).thenReturn(Optional.of(new Contract("Test")));
        // act
        ContractResponse contractResponse = contractService.findContract("Test");
        // assert
        assertThat(contractResponse.getTitle()).isEqualTo("Test");
    }

    @Test
    void shouldThrowExceptionForUnknownContract() {
        // arrange
        when(contractRepository.findContractByName("Unknown")).thenReturn(Optional.empty());
        // act & assert
        ContractNotFoundException exception =
                assertThrows(ContractNotFoundException.class, () -> contractService.findContract("Unknown"));
        assertThat(exception.getMessage()).isEqualTo("No contract found with name: Unknown");
    }

}
