package com.example.snoopsht.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, characteristics, price;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
