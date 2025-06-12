package com.codecamp.spring.app.template.db.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Collections;
import java.util.List;

public class Contract {

    @Id
    @Column("contract_id")
    private Long contractId;

    @Getter
    @Setter
    @Column("name")
    private String name;

    @MappedCollection // Spalte in Tabelle address
    private List<Address> addresses;

    public Contract() {
    }

    public Contract(String name, Address address) {
        this.name = name;
        this.addresses = Collections.singletonList(address);
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }
}
