package com.example.demospringboot.service;

import com.example.demospringboot.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmployeeService {
    Page<Employee> findAll(Pageable pageable);
    Employee findById(Long id);
    void create(Employee employee);
    void update(Employee employee);
    String formatDob(Employee employee);

    void removeById(Long id);

    Page<Employee> findByNameOrEmail(Pageable pageable, String searchText);
}
