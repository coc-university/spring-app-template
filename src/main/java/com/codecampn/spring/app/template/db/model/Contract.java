package com.codecampn.spring.app.template.db.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Getter
    @Column(name = "name", nullable = false)
    private String name;

    public Contract() {
    }

    public Contract(String name) {
        this.name = name;
    }
}
