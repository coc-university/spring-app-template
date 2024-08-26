package com.codecampn.spring.app.template.api.controller;

import com.codecampn.spring.app.template.api.model.ContractResponse;
import com.codecampn.spring.app.template.business.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContractController implements ContractApi {

    private final ContractService contractService;

    @Override
    public ResponseEntity<ContractResponse> getContract(String name) {
        ContractResponse response = contractService.findContract(name);
        return ResponseEntity.ok(response);
    }

}
