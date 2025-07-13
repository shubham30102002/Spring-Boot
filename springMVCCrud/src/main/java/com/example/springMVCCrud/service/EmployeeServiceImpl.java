package com.example.springMVCCrud.service;

import com.example.springMVCCrud.dao.EmployeeRepository;
import com.example.springMVCCrud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = repository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return repository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        repository.deleteById(theId);
    }
}