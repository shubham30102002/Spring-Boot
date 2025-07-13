package com.shubham.JPAJDBCDemo.entity.employee.repo;

import com.shubham.JPAJDBCDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
