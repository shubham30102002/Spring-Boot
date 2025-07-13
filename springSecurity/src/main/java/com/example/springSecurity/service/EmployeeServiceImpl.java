package com.example.springSecurity.service;

import com.example.springSecurity.dao.EmployeeDao;
import com.example.springSecurity.dao.EmployeeRepository;
import com.example.springSecurity.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeDao employeeDAO;

    @Autowired
    EmployeeRepository repository;

    @Override
    @Transactional
    public Employee save(Employee employee) {
        employeeDAO.save(employee);
        System.out.println("Employee saved in db "+ employee);
        return employee;
    }

    @Override
    public Employee findById(Integer id) {
        // return employeeDAO.findById(id);
        Optional<Employee> result = repository.findById(id);
        Employee employee = null;
        if(result.isPresent())
            employee = result.get();
        else
            throw new RuntimeException("Employee not found for id " + id);
        return employee;
    }

    public List<Employee> findAll(){
        //return employeeDAO.findAll();
        return repository.findAll();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        employeeDAO.delete(id);
    }
}
