package com.dev.RestCRUDDemo2.dao;

import com.dev.RestCRUDDemo2.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

public interface EmployeeDAO {
    void save(Employee employee);

    Employee findById(Integer id);

    List<Employee> findAll();

    void delete(Integer id);
}
