package com.codecamp.spring.app.template.integration.cucumber;

import io.cucumber.java.de.Angenommen;

public class ArrangeWorldSteps {

    @Angenommen("es geht um den Endpunkt {string}")
    public void endpoint(String endpoint) {
        World.endpoint = endpoint;
    }

    @Angenommen("ich bin authentifiziert")
    public void isAuthenticated() {
        World.isAuthenticated = true;
    }

    @Angenommen("ich bin nicht authentifiziert")
    public void isNotAuthenticated() {
        World.isAuthenticated = false;
    }

    @Angenommen("es geht um den Vertrag {string}")
    public void contract(String contract) {
        World.contractName = contract;
    }

}
