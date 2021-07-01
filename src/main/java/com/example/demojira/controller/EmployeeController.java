package com.example.demojira.controller;

import com.example.demojira.model.Employee;
import com.example.demojira.service.EmployeeService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;


@RestController
@Tag(name="Сотрудник", description="работает с сотрудниками")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //@PostMapping(value = "/employees")
    //@ResponseBody
    @Operation(
            summary = "Регистрация сотрудника",
            description = "Позволяет зарегистрировать сотрудника"
    )
    @RequestMapping(value = "/employees",  method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        employeeService.registrateEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(
            summary = "Получение списка сотрудников",
            description = "Позволяет получить всех зарегистрированных сотрудников"
    )
    @RequestMapping(value = "/employees",  method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> read() {
        final List<Employee> employees = employeeService.getAll();
        return employees != null && !employees.isEmpty()
                ? new ResponseEntity<>(employees, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Получение сотрудника",
            description = "Позволяет получить сотрудника по его id"
    )
    @RequestMapping(value = "/employees/{id}",  method = RequestMethod.GET)
    public ResponseEntity<Employee> read(@PathVariable(name = "id") @Parameter(description = "id сотрудника") @Min(1) Integer id) {
        final Employee employee = employeeService.getById(id);

        return employee != null
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //как правильно?
    @Operation(
            summary = "Редактирование сотрудника",
            description = "Позволяет изменить данные сотрудника"
    )
    @RequestMapping(value = "/employees/{id}",  method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable(name = "id") @Parameter(description = "id сотрудника") @Min(1) Integer id, @RequestBody Employee employee) {
        Boolean updated = employeeService.editEmployee(employee);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(
            summary = "Изменение статуса сотрудника",
            description = "Меняет статус сотрудника на противоположный"
    )

    //@Hidden
    @RequestMapping(value = "/employees/{id}/changeStatus",  method = RequestMethod.PUT)
    public ResponseEntity<?> changeStatus(@PathVariable(name = "id") @Parameter(description = "id сотрудника") @Min(1) Integer id) {
        employeeService.changeActive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
