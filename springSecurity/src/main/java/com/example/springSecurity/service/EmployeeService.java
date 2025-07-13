package com.example.springSecurity.service;

import com.example.springSecurity.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);

    Employee findById(Integer id);

    List<Employee> findAll();

    void delete(Integer id);
}