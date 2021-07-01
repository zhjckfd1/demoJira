package com.example.demojira.service;

import com.example.demojira.model.Employee;

import java.util.List;

public interface EmployeeService {

    void registrateEmployee(Employee employee);

    List<Employee> getAll();

    Employee getById(Integer employeeId);

    //update?
    Boolean editEmployee(Employee employee);
    
    void changeActive(Integer employeeId);
    
}
