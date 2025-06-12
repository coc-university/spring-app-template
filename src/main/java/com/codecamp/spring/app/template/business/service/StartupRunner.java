package com.codecamp.spring.app.template.business.service;

import com.codecamp.spring.app.template.api.model.ContractResponse;
import com.codecamp.spring.app.template.db.model.Address;
import com.codecamp.spring.app.template.db.model.Contract;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartupRunner implements CommandLineRunner {

    private final ContractService contractService;

    @Override
    public void run(String...args) throws Exception {
        log.info("StartupRunner is active");
        //contractService.updateContractNameWithTransaction("Versicherung ABC", "Versicherung neu");
        //contractService.updateContractNameWithSave("Versicherung ABC", "Versicherung neu");
        //contractService.resetContractName();

        //createContractWithAddress();
        //readContractWithAddress();
    }

    void createContractWithAddress() {
        Contract contract = new Contract();
        contract.setName("Contract with address");

        Address address = new Address();
        address.setStreet("Street of Address");
        address.setContract(contract);

        contract.setAddresses(List.of(address));

        contractService.createContract(contract);
    }

    void readContractWithAddress() {
        Contract contract = contractService.findContractEntity("Contract with address");
        log.info("Contract: {}", contract);
    }
}