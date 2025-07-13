package com.shubham.JPAJDBCDemo.entity.employee;

import com.shubham.JPAJDBCDemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    void save(Employee employee);

    Employee findById(Integer id);

    List<Employee> findAll();

    List<Employee> findByLastName(String lastName);

    void update(Employee employee);

    void delete(Integer id);

    int deleteAll();
}
