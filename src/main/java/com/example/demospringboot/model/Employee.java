package com.example.demospringboot.model;

import com.example.demospringboot.model.enumeration.EGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate dob;
    @Enumerated(value = EnumType.STRING)
    private EGender gender;
    @ManyToOne
    private Department department;
}
