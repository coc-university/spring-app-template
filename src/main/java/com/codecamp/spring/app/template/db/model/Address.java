package com.codecamp.spring.app.template.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class Address {

    @Id
    @Column("address_id")
    private Long addressId;

    @Column("street")
    private String street;

    public Address(String street) {
        this.street = street;
    }
}
