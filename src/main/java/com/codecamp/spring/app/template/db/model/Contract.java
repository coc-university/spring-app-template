package com.codecamp.spring.app.template.db.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(schema = "spring_app_template", name = "contract")
public class Contract {

    @Id
    private UUID id;

    @Getter
    private String name;

    public Contract() {
    }

    public Contract(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }
}
