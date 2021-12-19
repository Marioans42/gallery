package com.example.demo.service;

import com.example.demo.repository.model.Rubric;

import java.util.List;

public interface RubricService {
    Rubric findById(Integer id);
    List<Rubric> findAll();
}
