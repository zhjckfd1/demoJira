package com.example.demojira.service;

import com.example.demojira.model.Employee;

import java.util.List;

public interface EmployeeService {

    //назвать register?
    void addEmployee(Employee employee);

    List<Employee> getAll();

    Employee getById(Integer id);

    //update?
    //смена пароля?
    Boolean editEmployee(Employee employee);

    //delete?

    //добавить статус сотрудника(работает, заблокирован)
}
