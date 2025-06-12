package com.codecamp.spring.app.template.business.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartupRunner implements CommandLineRunner {

    private final ContractService contractService;

    @Override
    public void run(String...args) throws Exception {
        log.info("StartupRunner is active");
        //contractService.updateContractNameWithTransaction("Versicherung ABC", "Versicherung neu");
        contractService.updateContractNameWithSave("Versicherung ABC", "Versicherung neu");
        //contractService.resetContractName();
    }
}