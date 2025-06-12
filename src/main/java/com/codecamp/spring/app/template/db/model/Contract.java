package com.codecamp.spring.app.template.db.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "contract_id", updatable = false, nullable = false)
    private UUID contractId;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses;

    public Contract() {
    }

    public Contract(String name) {
        this.name = name;
    }
}
