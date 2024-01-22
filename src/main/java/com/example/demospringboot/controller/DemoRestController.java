package com.example.demospringboot.controller;


import com.example.demospringboot.model.Employee;
import com.example.demospringboot.repository.IEmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DemoRestController {
    private final IEmployeeRepository employeeRepository;

    @RequestMapping("/employees")
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);

    }
}
