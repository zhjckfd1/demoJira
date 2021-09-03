package com.example.demojira.service;

import com.example.demojira.components.HashWorkerMd5;
import com.example.demojira.dto.EmployeeGetDto;
import com.example.demojira.dto.EmployeeRegistrateDto;
import com.example.demojira.dto.EmployeeUpdateDto;
import com.example.demojira.exceptions.EntityAlreadyExistsException;
import com.example.demojira.exceptions.MyEntityNotFoundException;
import com.example.demojira.model.Employee;
import com.example.demojira.repository.EmployeeRepository;
import com.example.demojira.service.mapping.EmployeeGetDtoMapping;
import com.example.demojira.service.mapping.EmployeeRegistrateDtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final HashWorkerMd5 hashWorkerMd5;
    private final EmployeeGetDtoMapping employeeGetDtoMapping;
    private final EmployeeRegistrateDtoMapping employeeRegistrateDtoMapping;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               HashWorkerMd5 hashWorkerMd5,
                               EmployeeGetDtoMapping employeeGetDtoMapping,
                               EmployeeRegistrateDtoMapping employeeRegistrateDtoMapping){
        this.employeeRepository = employeeRepository;
        this.hashWorkerMd5 = hashWorkerMd5;
        this.employeeGetDtoMapping = employeeGetDtoMapping;
        this.employeeRegistrateDtoMapping = employeeRegistrateDtoMapping;
    }

    @Override
    @Transactional
    public void registrateEmployee(EmployeeRegistrateDto e) {
        Employee employee = employeeRegistrateDtoMapping.mapToEntity(e, hashWorkerMd5.md5Apache(e.getPassword()));
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
                .map(employeeGetDtoMapping::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeGetDto getById(Integer employeeId) {
        return employeeGetDtoMapping
                .mapToDto(employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new MyEntityNotFoundException(employeeId)));
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
                employee.setPassword(hashWorkerMd5.md5Apache(employeeDto.getPassword()));
            }
        }, () -> {
            throw new MyEntityNotFoundException(employeeId);
        });
    }

    @Override
    @Transactional
    public void changeActive(Integer employeeId) {
        Employee e = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new MyEntityNotFoundException(employeeId));
        e.setActive(!e.getActive());
    }


}
