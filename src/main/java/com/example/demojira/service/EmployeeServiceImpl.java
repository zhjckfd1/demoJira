package com.example.demojira.service;

import com.example.demojira.DTO.EmployeeGetDto;
import com.example.demojira.DTO.EmployeeRegistrateDto;
import com.example.demojira.model.Employee;
import com.example.demojira.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Boolean editEmployee(Integer employeeId, EmployeeRegistrateDto erd) {
        //getById(employee.getId())
        boolean update = employeeRepository.findById(employeeId).isPresent();
        if (update) {
            Employee employee = employeeRepository.getById(employeeId);
            employee.setLogin(erd.getLogin());
            employee.setPassword(erd.getPassword());
            employeeRepository.save(employee);
            return true;
        }
        else return false;
    }


    //@Transactional
    @Override
    public void changeActive(Integer employeeId) {
        Employee e = employeeRepository.getById(employeeId);
        e.setActive(!e.getActive());
        employeeRepository.save(e);
    }


}
