package com.example.demo.service.impl;

import com.example.demo.repository.dao.RubricRepository;
import com.example.demo.repository.model.Rubric;
import com.example.demo.service.RubricService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubricServiceImpl implements RubricService {
    private final RubricRepository rubricRepository;

    public RubricServiceImpl(RubricRepository rubricRepository) {
        this.rubricRepository = rubricRepository;
    }

    @Override
    public Rubric findById(Integer id) {
        return this.rubricRepository.findById(id).get();
    }

    @Override
    public List<Rubric> findAll() {
        return rubricRepository.findAll();
    }
}
