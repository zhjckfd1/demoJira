package com.example.demojira.service;

import com.example.demojira.dto.EmployeeRegistrateDto;
import com.example.demojira.dto.EmployeeGetDto;
import com.example.demojira.dto.EmployeeUpdateDto;
import com.example.demojira.exceptions.EntityAlreadyExistsException;

import java.util.List;

public interface EmployeeService {

    void registrateEmployee(EmployeeRegistrateDto employeeRegistrateDto) throws EntityAlreadyExistsException;

    List<EmployeeGetDto> getAll();

    EmployeeGetDto getById(Integer employeeId);

    void editEmployee(Integer employeeId, EmployeeUpdateDto employeeDto);

    void changeActive(Integer employeeId);
}
