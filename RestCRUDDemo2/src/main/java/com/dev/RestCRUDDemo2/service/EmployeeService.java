package com.dev.RestCRUDDemo2.service;

import com.dev.RestCRUDDemo2.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);

    Employee findById(Integer id);

    List<Employee> findAll();

    void delete(Integer id);
}
