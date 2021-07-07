package com.example.demojira.service;

import com.example.demojira.components.HashWorkerMd5;
//import com.example.demojira.config.TestConfig;
import com.example.demojira.dto.EmployeeGetDto;
import com.example.demojira.dto.EmployeeRegistrateDto;
import com.example.demojira.dto.EmployeeUpdateDto;
import com.example.demojira.exceptions.EntityAlreadyExistsException;
import com.example.demojira.model.Employee;
import com.example.demojira.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //@Inject
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HashWorkerMd5 hw;

    //@Transactional
    @Override
    public void registrateEmployee(EmployeeRegistrateDto e) {
        //HashWorkerMd5 hw = (HashWorkerMd5) context.getBean("getHashWorkerMd5");
        //System.out.println(hw.md5Apache("42"));

        //меняем сразу в DTO?
        Employee employee = MappingUtils.mapToEntityFromEmployeeRegistrateDto(e, hw.md5Apache(e.getPassword()));
        if (employeeRepository.findByLogin(employee.getLogin()) == null) {
            employeeRepository.save(employee);
        } else {
            throw new EntityAlreadyExistsException();
        }
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
            employee.setPassword(hw.md5Apache(employeeDto.getPassword()));
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
