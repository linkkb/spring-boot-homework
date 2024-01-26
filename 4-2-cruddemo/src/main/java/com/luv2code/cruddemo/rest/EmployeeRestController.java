package com.luv2code.cruddemo.rest;

import com.luv2code.cruddemo.dao.EmployeeDAO;
import com.luv2code.cruddemo.entity.Employee;
import com.luv2code.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //expose /employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee findById(@PathVariable int id) {
        return employeeService.findById(id);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

}
