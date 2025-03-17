package com.codecamp.spring.app.template.api.controller;

import com.codecamp.spring.app.template.api.model.ContractResponse;
import com.codecamp.spring.app.template.business.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ContractController implements ContractApi, ContractsApi {

    private final ContractService contractService;

    @Override
    public Mono<ContractResponse> getContract(
            String name,
            ServerWebExchange exchange
    ) {
        return contractService
                .findContract(name)
                .doOnSubscribe(ContractController::logSubscribe);
    }

    @Override
    public Flux<ContractResponse> getContracts(ServerWebExchange exchange) {
        return contractService
                .findAllContracts()
                .doOnSubscribe(ContractController::logSubscribe);
    }

    private static void logSubscribe(Subscription ignoredSubscription) {
        log.info("subscribed, so open stream now");
    }

}
