package com.codecamp.spring.app.template.db.repository;

import com.codecamp.spring.app.template.db.model.Contract;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContractRepository extends ListCrudRepository<Contract, Long> {

    Optional<Contract> findContractByName(String name);
}
