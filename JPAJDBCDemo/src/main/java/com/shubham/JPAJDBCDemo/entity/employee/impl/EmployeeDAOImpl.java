package com.shubham.JPAJDBCDemo.entity.employee.impl;

import com.shubham.JPAJDBCDemo.entity.Employee;
import com.shubham.JPAJDBCDemo.entity.employee.EmployeeDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Employee employee){
        entityManager.persist(employee);
    }

    @Override
    public Employee findById(Integer id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee where lastName=:lastName",
                Employee.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int rowAffected= entityManager.createQuery("DELETE FROM Employee").executeUpdate();
        return rowAffected;
    }
}
