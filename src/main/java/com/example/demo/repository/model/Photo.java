package com.example.demo.repository.model;

import javax.persistence.*;


@Entity(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_photo")
    private Integer id;

    @Column(name = "name_photo")
    private String name;

    @Lob
    @Column(name = "data_photo")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "id_rubrique")
    private Rubric rubric;

    public Photo() {
    }

    public Photo(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    public Photo(String name, byte[] data, Rubric rubric) {
        this.name = name;
        this.data = data;
        this.rubric = rubric;
    }

    public Photo(Integer id, String name, byte[] data, Rubric rubric) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.rubric = rubric;
    }

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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Rubric getRubric() {
        return rubric;
    }

    public void setRubric(Rubric rubric) {
        this.rubric = rubric;
    }
}
