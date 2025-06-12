package com.codecamp.spring.app.template.db.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id", updatable = false, nullable = false)
    private UUID addressId;

    @Getter
    @Setter
    @Column(name = "street", nullable = false)
    private String street;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
}
