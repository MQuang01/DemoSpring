package com.example.demospringboot.service.Impl;

import com.example.demospringboot.model.Department;
import com.example.demospringboot.repository.IDepartmentRepository;
import com.example.demospringboot.service.IDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService {
    private final IDepartmentRepository departmentRepository;
    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void create(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new RuntimeException("Tên đã tồn tại");
        }
        departmentRepository.save(department);
    }
}
