package com.example.demo.repository.model;

import javax.persistence.*;


@Entity(name = "rubrique")
public class Rubric {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rubrique")
    private Integer id;

    @Column(name = "name_rubrique")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
