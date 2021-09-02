package com.example.demojira.controller;

import com.example.demojira.dto.*;
import com.example.demojira.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Tag(name="Отчет", description="работает с отчетами сотрудников")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @Operation(
            summary = "Создание отчета",
            description = "Позволяет создать отчет"
    )
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/reports",  method = RequestMethod.POST)
    public String create(@RequestBody @Valid ReportAddDto reportAddDto) {
        reportService.createReport(reportAddDto);
        return "created";
    }

    @Operation(
            summary = "Получение списка отчетов",
            description = "Позволяет получить все созданные отчеты"
    )
    @RequestMapping(value = "/reports",  method = RequestMethod.GET)
    public ResponseEntity<List<ReportGetDto>> readAll() {
        final List<ReportGetDto> reports = reportService.getAll();
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение списка отчетов к задаче",
            description = "Позволяет получить все отчеты к задаче"
    )
    @RequestMapping(value = "/reports/tasks/{taskId}",  method = RequestMethod.GET)
    public ResponseEntity<List<ReportGetDto>> readTaskReports(
            @PathVariable(name = "taskId") @Parameter(description = "id задачи") @Min(1) Integer taskId) {
        final List<ReportGetDto> reports = reportService.getAllReportsOnTheTaskId(taskId);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение списка отчетов пользователя",
            description = "Позволяет получить все отчеты пользователя"
    )
    @RequestMapping(value = "/reports/employees/{employeeId}",  method = RequestMethod.GET)
    public ResponseEntity<List<ReportGetDto>> readEmployeeReports(
            @PathVariable(name = "employeeId") @Parameter(description = "id сотрудника") @Min(1) Integer employeeId) {
        final List<ReportGetDto> reports = reportService.getAllReportsOnTheEmployeeId(employeeId);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение отчета",
            description = "Позволяет получить отчет по его id"
    )
    @RequestMapping(value = "/reports/{id}",  method = RequestMethod.GET)
    public ResponseEntity<ReportGetDto> read(
            @PathVariable(name = "id") @Parameter(description = "id отчета") @Min(1) Integer id) {
        final ReportGetDto report = reportService.getById(id);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение отчета по критериям",
            description = "Позволяет получить отчет по списку критериев"
    )
    @RequestMapping(value = "/reports/getByCriteria",  method = RequestMethod.GET)
    public ResponseEntity<List<ReportGetDto>> readByCriteria(@RequestBody @Valid ReportCriteriaDto reportCriteriaDto){
        final List<ReportGetDto> reports = reportService.getAllByCriteria(reportCriteriaDto);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }
}
