package com.example.demojira.service;

import com.example.demojira.model.Employee;
import com.example.demojira.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void addEmployee(Employee employee) {
        employee.setRegisteredDate(new Date());
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        return employeeRepository.getById(id);
    }


    //как правильно?
    @Override
    public Boolean editEmployee(Employee employee) {
        boolean update = employeeRepository.findById(employee.getId()).isPresent();
        if (update) {
            employeeRepository.save(employee);
            return true;
        }
        else return false;
    }


}
