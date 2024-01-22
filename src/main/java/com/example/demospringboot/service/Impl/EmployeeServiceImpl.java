package com.example.demospringboot.service.Impl;

import com.example.demospringboot.model.Employee;
import com.example.demospringboot.repository.IDepartmentRepository;
import com.example.demospringboot.repository.IEmployeeRepository;
import com.example.demospringboot.service.IDepartmentService;
import com.example.demospringboot.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;
    private final IDepartmentRepository departmentRepository;

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Employee employee) {
        if (!departmentRepository.existsById(employee.getDepartment().getId())) {
            throw new RuntimeException("Department not found");
        }
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        if (employeeRepository.existsByEmailAndIdNot(employee.getEmail(), employee.getId())) {
            throw new RuntimeException("Email already exists");
        }
        employeeRepository.save(employee);
    }

    @Override
    public String formatDob(Employee employee) {
        return String.valueOf(employee.getDob());
    }

    @Override
    public void removeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<Employee> findByNameOrEmail(Pageable pageable, String searchText) {
        return employeeRepository.findByNameContainingOrEmailContaining( searchText, searchText, pageable);
    }
}
