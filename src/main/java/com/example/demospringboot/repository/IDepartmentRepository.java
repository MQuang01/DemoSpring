package com.example.demospringboot.repository;

import com.example.demospringboot.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);
}
