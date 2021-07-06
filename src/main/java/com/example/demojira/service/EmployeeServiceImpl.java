package com.example.demojira.service;

import com.example.demojira.dto.EmployeeGetDto;
import com.example.demojira.dto.EmployeeRegistrateDto;
import com.example.demojira.dto.EmployeeUpdateDto;
import com.example.demojira.model.Employee;
import com.example.demojira.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    //@Inject
    @Autowired
    private EmployeeRepository employeeRepository;

    //@Transactional
    @Override
    public void registrateEmployee(EmployeeRegistrateDto e) {
        Employee employee = MappingUtils.mapToEntityFromEmployeeRegistrateDto(e);
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeGetDto> getAll() {
        return employeeRepository.findAll().stream().map(MappingUtils::mapToEmployeeGetDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeGetDto getById(Integer employeeId) {
        return MappingUtils.mapToEmployeeGetDto(employeeRepository.getById(employeeId));
    }


    //как правильно?
    //@Transactional
    @Override
    public void editEmployee(Integer employeeId, EmployeeUpdateDto employeeDto) {
        employeeRepository.findById(employeeId).ifPresentOrElse(employee -> {
            employee.setPassword(employeeDto.getPassword());
            employeeRepository.save(employee);
        }, () -> {
            throw new EntityNotFoundException();
        });
    }


    //@Transactional
    @Override
    public void changeActive(Integer employeeId) {
        Employee e = employeeRepository.getById(employeeId);
        e.setActive(!e.getActive());
        employeeRepository.save(e);
    }


}
