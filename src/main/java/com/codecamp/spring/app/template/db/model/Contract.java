package com.codecamp.spring.app.template.db.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    public Contract() {
    }

    public Contract(String name) {
        this.name = name;
    }
}
