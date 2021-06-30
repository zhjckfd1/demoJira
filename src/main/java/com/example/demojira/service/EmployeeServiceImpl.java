package com.example.demojira.service;

import com.example.demojira.model.Employee;
import com.example.demojira.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        employee.setStatus(true);
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
    public void changeStatus(Integer id_employee) {
        Employee e = getById(id_employee);
        e.setStatus(!e.getStatus());
        employeeRepository.save(e);

    }


}
