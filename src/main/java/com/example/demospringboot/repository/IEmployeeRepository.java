package com.example.demospringboot.repository;

import com.example.demospringboot.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);

    Page<Employee> findByNameContainingOrEmailContaining(String name, String email, Pageable pageable);
}
