package com.example.demospringboot.service;

import com.example.demospringboot.model.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findAll();
    Department findById(Long id);
    void create(Department department);
}
