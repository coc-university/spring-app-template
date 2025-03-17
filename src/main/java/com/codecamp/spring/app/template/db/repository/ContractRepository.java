package com.codecamp.spring.app.template.db.repository;

import com.codecamp.spring.app.template.db.model.Contract;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@EnableR2dbcRepositories
public interface ContractRepository extends R2dbcRepository<Contract, UUID> {

    Mono<Contract> findContractByName(String name);
}
