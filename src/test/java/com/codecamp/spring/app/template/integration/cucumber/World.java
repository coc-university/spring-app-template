package com.codecamp.spring.app.template.integration.cucumber;

import com.codecamp.spring.app.template.api.model.ContractResponse;
import org.springframework.http.ResponseEntity;

public class World {

    public static String endpoint;
    public static boolean isAuthenticated;
    public static ResponseEntity<ContractResponse> responseEntity;
}
