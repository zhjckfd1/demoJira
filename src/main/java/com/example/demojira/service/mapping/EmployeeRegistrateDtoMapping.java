package com.example.demojira.service.mapping;

import com.example.demojira.dto.EmployeeRegistrateDto;
import com.example.demojira.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeRegistrateDtoMapping {
    public Employee mapToEntity(EmployeeRegistrateDto employeeRegistrateDto, String password) {
        Employee employee = new Employee();
        employee.setLogin(employeeRegistrateDto.getLogin());
        employee.setPassword(password);
        employee.setFirstname(employeeRegistrateDto.getFirstname());
        employee.setSurname(employeeRegistrateDto.getSurname());
        employee.setPatronymic(employeeRegistrateDto.getPatronymic());
        employee.setRegisteredDate(new Date());
        employee.setActive(true);
        return employee;
    }
}
