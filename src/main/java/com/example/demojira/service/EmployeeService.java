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

    //use Number(1)    +
    //CHAR(1)  и 'Y' / 'N'     (реализовать 2 действия (заблокировать и восстановить), используем true/false  ?)
    void changeStatus(Integer id_employee);


    //добавить статус сотрудника(работает, заблокирован)
}
