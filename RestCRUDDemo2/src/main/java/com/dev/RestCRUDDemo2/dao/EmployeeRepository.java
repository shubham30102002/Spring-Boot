package com.dev.RestCRUDDemo2.dao;

import com.dev.RestCRUDDemo2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // that's it ... no need to write any code LOL!
}
