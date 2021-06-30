package com.example.demojira.controller;

import com.example.demojira.model.Employee;
import com.example.demojira.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //@PostMapping(value = "/employees")
    //@ResponseBody
    @RequestMapping(value = "/employees",  method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/employees",  method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> read() {
        final List<Employee> employees = employeeService.getAll();
        return employees != null && !employees.isEmpty()
                ? new ResponseEntity<>(employees, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/employees/{id}",  method = RequestMethod.GET)
    public ResponseEntity<Employee> read(@PathVariable(name = "id") Integer id) {
        final Employee employee = employeeService.getById(id);

        return employee != null
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //как правильно?
    @RequestMapping(value = "/employees/{id}",  method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody Employee employee) {
        Boolean updated = employeeService.editEmployee(employee);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    /*
    @RequestMapping(value = "/employees/{id}/changeStatus",  method = RequestMethod.PUT)
    public ResponseEntity<?> changeStatus(@PathVariable(name = "id") Integer id) {
        employeeService.changeStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
}
