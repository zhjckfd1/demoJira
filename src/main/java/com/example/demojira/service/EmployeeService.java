package com.example.demojira.service;

import com.example.demojira.dto.EmployeeRegistrateDto;
import com.example.demojira.dto.EmployeeGetDto;
import com.example.demojira.dto.EmployeeUpdateDto;

import java.util.List;

public interface EmployeeService {

    void registrateEmployee(EmployeeRegistrateDto employeeRegistrateDto);

    List<EmployeeGetDto> getAll();

    EmployeeGetDto getById(Integer employeeId);

    //update?
    void editEmployee(Integer employeeId, EmployeeUpdateDto employeeDto);

    void changeActive(Integer employeeId);

    //получить всех сотрудников с состоянием?
    
}
