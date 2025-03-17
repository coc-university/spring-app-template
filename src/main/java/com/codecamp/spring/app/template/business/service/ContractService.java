package com.codecamp.spring.app.template.business.service;

import com.codecamp.spring.app.template.api.model.ContractResponse;
import com.codecamp.spring.app.template.business.exception.ContractNotFoundException;
import com.codecamp.spring.app.template.db.model.Contract;
import com.codecamp.spring.app.template.db.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;

    public Mono<ContractResponse> findContract(String name) {
        return contractRepository.findContractByName(name)
                .switchIfEmpty(Mono.error(new ContractNotFoundException("No contract found with name: " + name)))
                .map(this::mapContractToContractResponse)
                .doOnSuccess(contractResponse ->
                        log.info("found contract with name: {}", contractResponse.getTitle()));
    }

    // decouple internal structure from the outside (entity to response DTO)
    // for bigger projects you can use tools like mapstruct
    private ContractResponse mapContractToContractResponse(Contract contract) {
        ContractResponse response = new ContractResponse();
        response.setTitle(contract.getName()); // just as example, map name to title
        return response;
    }

}
