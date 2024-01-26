package com.luv2code.cruddemo.service;

import com.luv2code.cruddemo.dao.EmployeeDAO;
import com.luv2code.cruddemo.dao.EmployeeRepository;
import com.luv2code.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RuntimeException("Did not find employee id - " + id);
        }
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }



    /*
    EmployeeDAO employeeDAO;
    @Autowired
    EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }
     */
}
