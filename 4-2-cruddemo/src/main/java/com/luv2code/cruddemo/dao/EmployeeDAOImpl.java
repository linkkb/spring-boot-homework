package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private EntityManager entityManager;
    @Autowired
    EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Employee> findAll() {
        //create query
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        //execute query
        List<Employee> employees = query.getResultList();
        //return results
        return employees;
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }
}
