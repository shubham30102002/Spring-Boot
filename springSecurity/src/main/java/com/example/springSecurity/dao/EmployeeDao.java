package com.example.springSecurity.dao;

import com.example.springSecurity.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    void save(Employee employee);

    Employee findById(Integer id);

    List<Employee> findAll();

    void delete(Integer id);
}
