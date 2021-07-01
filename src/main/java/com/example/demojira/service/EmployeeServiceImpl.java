package com.example.demojira.service;

import com.example.demojira.model.Employee;
import com.example.demojira.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    //@Inject
    @Autowired
    private EmployeeRepository employeeRepository;

    //@Transactional
    @Override
    public void registrateEmployee(Employee employee) {
        employee.setRegisteredDate(new Date());
        employee.setActive(true);
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Integer employeeId) {
        return employeeRepository.getById(employeeId);
    }


    //как правильно?
    //@Transactional
    @Override
    public Boolean editEmployee(Employee employee) {
        //getById(employee.getId())
        boolean update = employeeRepository.findById(employee.getId()).isPresent();
        if (update) {
            employeeRepository.save(employee);
            return true;
        }
        else return false;
    }

    //@Transactional
    @Override
    public void changeActive(Integer employeeId) {
        Employee e = getById(employeeId);
        e.setActive(!e.getActive());
        employeeRepository.save(e);
    }


}
