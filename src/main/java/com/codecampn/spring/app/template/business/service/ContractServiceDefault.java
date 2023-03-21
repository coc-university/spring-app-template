package com.codecampn.spring.app.template.business.service;

import com.codecampn.spring.app.template.api.model.ContractResponse;
import com.codecampn.spring.app.template.business.exception.ContractNotFoundException;
import com.codecampn.spring.app.template.db.model.Contract;
import com.codecampn.spring.app.template.db.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContractServiceDefault implements ContractService {

    private final ContractRepository contractRepository;

    @Override
    public ContractResponse findContract(String name) {
        Contract contract = contractRepository.findContractByName(name)
                .orElseThrow(() -> new ContractNotFoundException("No contract found with name: " + name));
        log.info("found contract with name: {}", contract.getName());
        return mapContractToContractResponse(contract);
    }

    // decouple internal structure from the outside (entity to response DTO)
    // for bigger projects you can use tools like mapstruct
    private ContractResponse mapContractToContractResponse(Contract contract) {
        ContractResponse response = new ContractResponse();
        response.setTitle(contract.getName()); // just as example, map name to title
        return response;
    }

}
