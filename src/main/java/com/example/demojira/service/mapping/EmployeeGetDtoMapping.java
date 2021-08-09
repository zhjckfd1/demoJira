package com.example.demojira.service.mapping;

import com.example.demojira.dto.EmployeeGetDto;
import com.example.demojira.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeGetDtoMapping {
    public EmployeeGetDto mapToDto(Employee entity) {
        EmployeeGetDto employeeDto = new EmployeeGetDto();
        employeeDto.setLogin(entity.getLogin());
        employeeDto.setRegisteredDate(entity.getRegisteredDate());
        employeeDto.setActive(entity.getActive());
        employeeDto.setFirstname(entity.getFirstname());
        employeeDto.setSurname(entity.getSurname());
        employeeDto.setPatronymic(entity.getPatronymic());
        return employeeDto;
    }
}
