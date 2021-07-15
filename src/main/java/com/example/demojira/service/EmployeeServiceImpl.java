package com.example.demojira.service;

import com.example.demojira.components.HashWorkerMd5;
import com.example.demojira.dto.EmployeeGetDto;
import com.example.demojira.dto.EmployeeRegistrateDto;
import com.example.demojira.dto.EmployeeUpdateDto;
import com.example.demojira.exceptions.EntityAlreadyExistsException;
import com.example.demojira.exceptions.MyEntityNotFoundException;
import com.example.demojira.model.Employee;
import com.example.demojira.repository.EmployeeRepository;
import com.example.demojira.service.mapping.MappingEmployeeGetDto;
import com.example.demojira.service.mapping.MappingEmployeeRegistrateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HashWorkerMd5 hw;

    @Autowired
    private MappingEmployeeGetDto mappingEmployeeGetDto;

    @Autowired
    private MappingEmployeeRegistrateDto mappingEmployeeRegistrateDto;

    @Override
    @Transactional
    public void registrateEmployee(EmployeeRegistrateDto e) {
        Employee employee = mappingEmployeeRegistrateDto.mapToEntity(e, hw.md5Apache(e.getPassword()));
        if (employeeRepository.findByLogin(employee.getLogin()) == null) {
            employeeRepository.save(employee);
        } else {
            throw new EntityAlreadyExistsException();
        }
    }

    @Override
    @Transactional
    public List<EmployeeGetDto> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(mappingEmployeeGetDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeGetDto getById(Integer employeeId) {
        return mappingEmployeeGetDto.mapToDto(employeeRepository.getById(employeeId));
    }

    @Override
    @Transactional
    public void editEmployee(Integer employeeId, EmployeeUpdateDto employeeDto) {
        employeeRepository.findById(employeeId).ifPresentOrElse(employee -> {
            if (employeeDto.getFirstname() != null) {
                employee.setFirstname(employeeDto.getFirstname());
            }
            if (employeeDto.getSurname() != null) {
                employee.setSurname(employeeDto.getSurname());
            }
            if (employeeDto.getPatronymic() != null) {
                employee.setPatronymic(employeeDto.getPatronymic());
            }
            if (employeeDto.getPassword() != null) {
                employee.setPassword(hw.md5Apache(employeeDto.getPassword()));
            }
        }, () -> {
            throw new MyEntityNotFoundException();
        });
    }

    @Override
    @Transactional
    public void changeActive(Integer employeeId) {
        Employee e = employeeRepository.getById(employeeId);
        e.setActive(!e.getActive());
    }


}
