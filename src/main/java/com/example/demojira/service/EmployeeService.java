package com.example.demojira.service;

import com.example.demojira.DTO.EmployeeRegistrateDto;
import com.example.demojira.DTO.EmployeeGetDto;

import java.util.List;

public interface EmployeeService {

    void registrateEmployee(EmployeeRegistrateDto employeeRegistrateDto);

    List<EmployeeGetDto> getAll();

    EmployeeGetDto getById(Integer employeeId);

    //update?
    Boolean editEmployee(Integer employeeId, EmployeeRegistrateDto employeeDto);

    void changeActive(Integer employeeId);

    //получить всех сотрудников с состоянием?
    
}
