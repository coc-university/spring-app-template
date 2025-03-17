package com.codecamp.spring.app.template.api.controller;

import com.codecamp.spring.app.template.api.model.ContractResponse;
import com.codecamp.spring.app.template.business.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ContractController implements ContractApi {

    private final ContractService contractService;

    @Override
    public Mono<ContractResponse> getContract(
            String name,
            ServerWebExchange exchange
    ) {
        return contractService.findContract(name);
    }

}
