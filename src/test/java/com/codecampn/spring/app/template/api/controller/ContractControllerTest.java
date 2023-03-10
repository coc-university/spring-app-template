package com.codecampn.spring.app.template.api.controller;

import com.codecampn.spring.app.template.api.model.ContractResponse;
import com.codecampn.spring.app.template.business.service.ContractService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ContractController.class) // slice test
class ContractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContractService contractService;

    @Test
    void shouldReturnContractResponse() throws Exception {
        when(contractService.findContract(any())).thenReturn(new ContractResponse().title("Versicherung ABC"));
        mockMvc.perform(get("/v1/contract").param("name", "Versicherung ABC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Versicherung ABC")));
    }
}
