package com.codecampn.spring.app.template.business.service;

import com.codecampn.spring.app.template.api.model.ContractResponse;

public interface ContractService {

    ContractResponse findContract(String name);
}
